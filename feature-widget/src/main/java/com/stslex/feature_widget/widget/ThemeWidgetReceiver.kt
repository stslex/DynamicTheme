package com.stslex.feature_widget.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.stslex.feature_widget.worker.ThemeWorker

class ThemeWidgetReceiver(
    override val glanceAppWidget: GlanceAppWidget = ThemeWidget()
) : GlanceAppWidgetReceiver() {

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        ThemeWorker.enqueue(context)
    }

    /**
     * Called when the last instance of this widget is removed.
     * Make sure to cancel all ongoing workers when user remove all widget instances
     */
    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        ThemeWorker.cancel(context)
    }
}

