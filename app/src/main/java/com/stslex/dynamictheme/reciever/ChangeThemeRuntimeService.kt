package com.stslex.dynamictheme.reciever

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.IBinder
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.CustomTarget
import com.slex.dynamictheme.R
import com.stslex.core.AppLogger
import com.stslex.core_data_source.dao.ThemeImageDao
import com.stslex.core_data_source.model.ThemeTypeEntity
import com.stslex.dynamictheme.utils.CoroutineExt.launchOrInvoke
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.inject

//TODO FOR WIDGETS
class ChangeThemeRuntimeService : Service() {

    private val themeImageDao: ThemeImageDao by inject()
    private val glideTargetFactory: GlideReceiverCustomTarget.Factory by inject()
    private val glideTarget: CustomTarget<Bitmap> by lazy {
        glideTargetFactory.create(scope, ::cancelNotification)
    }

    private val scope = CoroutineScope(SupervisorJob())
    private var receiverJob: Job? = null

    private var _themeType: ThemeTypeEntity? = null
    private val themeType: ThemeTypeEntity
        get() = requireNotNull(_themeType)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP_LISTEN) {
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
            return START_NOT_STICKY
        }

        getSystemService(NotificationManager::class.java).apply {
            createNotificationChannel()
            notify(ONGOING_NOTIFICATION_ID, notification)
        }

        AppLogger.logInfo("receive: $intent", javaClass.simpleName)

        _themeType = intent?.getStringExtra(KEY_THEME_TYPE)?.let(ThemeTypeEntity::valueOf)
            ?: ThemeTypeEntity.DARK

        receiverJob = Glide
            .with(this)
            .asBitmap()
            .startImageReceiverJob()

        return super.onStartCommand(intent, flags, startId)
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

    override fun onBind(intent: Intent?): IBinder? = null

    private val notification: Notification
        get() = Notification.Builder(this, ThemeChangeService.CHANNEL_ID)
            .setContentTitle(getText(R.string.notification_title))
            .setContentText(getText(R.string.notification_message))
            .setSmallIcon(R.drawable.baseline_wallpaper_24)
            .setTicker(getText(R.string.ticker_text))
            .setActions(closeAction)
            .build()

    private val closeAction: Notification.Action
        get() = Notification.Action.Builder(
            Icon.createWithResource(this, R.drawable.baseline_wallpaper_24),
            "close",
            closeIntent
        ).build()

    private val closeIntent: PendingIntent
        get() = Intent(
            this,
            ThemeChangeService::class.java
        ).let { actionIntent ->
            actionIntent.action = ACTION_STOP_LISTEN
            PendingIntent.getService(
                this,
                ONGOING_NOTIFICATION_ID,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

    private fun NotificationManager.createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID, "Foreground Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        createNotificationChannel(serviceChannel)
    }

    private fun cancelNotification() {
        closeIntent.send()
    }

    companion object {
        private const val ONGOING_NOTIFICATION_ID = 132123
        private const val ACTION_STOP_LISTEN = "action_stop_listen"
        private const val CHANNEL_ID = "ForegroundRuntimeServiceChannel"
        const val KEY_THEME_TYPE = "KEY_THEME_TYPE"
    }
}