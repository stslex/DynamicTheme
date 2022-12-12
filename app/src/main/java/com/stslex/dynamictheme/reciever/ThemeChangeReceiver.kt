package com.stslex.dynamictheme.reciever

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.stslex.core.AppLogger
import com.stslex.core_data_source.dao.ThemeImageDao
import com.stslex.core_data_source.model.ThemeTypeEntity
import com.stslex.dynamictheme.utils.CoroutineExt.launchOrInvoke
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.java.KoinJavaComponent.inject

class ThemeChangeReceiver : BroadcastReceiver() {

    private val themeImageDao: ThemeImageDao by inject(ThemeImageDao::class.java)
    private val scope = CoroutineScope(SupervisorJob())
    private var receiverJob: Job? = null
    private var _themeType: ThemeTypeEntity? = null
    private val themeType: ThemeTypeEntity
        get() = requireNotNull(_themeType)

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        AppLogger.logInfo("receive: $intent", javaClass.simpleName)

        val currentType = context.currentUiType ?: return
        if (_themeType == currentType) return
        else _themeType = currentType

        val target = GlideCustomTarget(
            wallpaperManager = WallpaperManager.getInstance(context),
            scope = scope
        )
        val requestBuilder = Glide.with(context).asBitmap()

        receiverJob = scope.launchOrInvoke(
            job = receiverJob,
            context = Dispatchers.IO,
            start = CoroutineStart.DEFAULT,
            block = {
                requestBuilder.startImageJob(target)
            }
        )
    }

    private suspend fun RequestBuilder<Bitmap>.startImageJob(target: GlideCustomTarget) {
        val uri = themeImageDao.getAllImages().firstOrNull { it.themeType == themeType }?.uri
        load(Uri.parse(uri)).into(target)
    }

    private val Context.currentUiType: ThemeTypeEntity?
        get() = when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> ThemeTypeEntity.LIGHT
            Configuration.UI_MODE_NIGHT_YES -> ThemeTypeEntity.DARK
            else -> null
        }
}