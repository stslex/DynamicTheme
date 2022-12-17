package com.stslex.feature_widget.base

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.stslex.feature_widget.worker.ThemeWorker

class UpdateWidgetAction : ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        ThemeWorker.enqueue(context = context, force = true)
    }
}