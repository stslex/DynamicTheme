package com.stslex.dynamictheme.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.stslex.dynamictheme.ui.widget.ThemeWidget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PackageReplacedBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != Intent.ACTION_MY_PACKAGE_REPLACED) return
        val currentContext = context ?: return
        CoroutineScope(SupervisorJob()).launch {
            GlanceAppWidgetManager(currentContext)
                .getGlanceIds(ThemeWidget::class.java)
                .firstOrNull()?.let { glanceId ->
                    ThemeWidget().update(currentContext, glanceId)
                }
        }
    }
}