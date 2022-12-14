package com.stslex.dynamictheme.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.CustomTarget
import com.stslex.core.AppLogger
import com.stslex.core_data_source.dao.ThemeImageDao
import com.stslex.core_data_source.model.ThemeTypeEntity
import com.stslex.dynamictheme.utils.CoroutineExt.launchOrInvoke
import com.stslex.dynamictheme.utils.PrimaryExt.nullIfSameOrNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.java.KoinJavaComponent.inject

class ThemeChangeReceiver : BroadcastReceiver() {

    private val themeImageDao: ThemeImageDao by inject(ThemeImageDao::class.java)
    private val glideTargetFactory: GlideReceiverCustomTarget.Factory by inject(
        GlideReceiverCustomTarget.Factory::class.java
    )
    private val glideTarget: CustomTarget<Bitmap> by lazy {
        glideTargetFactory.create(scope, ::cancelNotification)
    }

    private val scope = CoroutineScope(SupervisorJob())
    private var receiverJob: Job? = null

    private var _themeType: ThemeTypeEntity? = null
    private val themeType: ThemeTypeEntity
        get() = requireNotNull(_themeType)

    override fun onReceive(context: Context, intent: Intent) {
        AppLogger.logInfo("receive: $intent", javaClass.simpleName)

        _themeType = _themeType.nullIfSameOrNull(context.currentUiType) ?: return
        receiverJob = Glide
            .with(context)
            .asBitmap()
            .startImageReceiverJob()
    }

    private fun RequestBuilder<Bitmap>.startImageReceiverJob(): Job =
        scope.launchOrInvoke(
            job = receiverJob,
            context = Dispatchers.IO,
            start = CoroutineStart.DEFAULT,
            block = {
                val uri = themeImageDao.getImageUri(themeType).let(Uri::parse)
                load(uri).into(glideTarget)
                // TODO send notification
            }
        )

    private val Context.currentUiType: ThemeTypeEntity?
        get() = when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> ThemeTypeEntity.LIGHT
            Configuration.UI_MODE_NIGHT_YES -> ThemeTypeEntity.DARK
            else -> null
        }

    private fun cancelNotification() {
        //TODO close notification
    }
}