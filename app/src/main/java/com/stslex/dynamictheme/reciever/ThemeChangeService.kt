package com.stslex.dynamictheme.reciever

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.os.IBinder
import com.slex.dynamictheme.R
import com.stslex.dynamictheme.ui.MainActivity


class ThemeChangeService : Service() {

    companion object {
        private const val ONGOING_NOTIFICATION_ID = 13212
        private const val ACTION_STOP_LISTEN = "action_stop_listen"
        const val CHANNEL_ID = "ForegroundServiceChannel"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP_LISTEN) {
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
            return START_NOT_STICKY
        }
        createNotificationChannel()
        startForeground(ONGOING_NOTIFICATION_ID, notification)
        registerReceiver(
            ThemeChangeReceiver(),
            IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED)
        )
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private val notification: Notification
        get() = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(getText(R.string.notification_title))
            .setContentText(getText(R.string.notification_message))
            .setSmallIcon(R.drawable.baseline_wallpaper_24)
            .setContentIntent(contentIntent)
            .setTicker(getText(R.string.ticker_text))
            .setActions(closeAction)
            .build()

    private val contentIntent: PendingIntent
        get() = Intent(
            this,
            MainActivity::class.java
        ).let { notificationIntent ->
            PendingIntent.getActivity(
                this, 0, notificationIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }

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

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "Foreground Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(
            NotificationManager::class.java
        )
        manager.createNotificationChannel(serviceChannel)
    }
}