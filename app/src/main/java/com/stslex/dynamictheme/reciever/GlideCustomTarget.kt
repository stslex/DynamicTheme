package com.stslex.dynamictheme.reciever

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.stslex.core.AppLogger
import com.stslex.dynamictheme.utils.CoroutineExt.launchOrInvoke
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class GlideCustomTarget(
    private val wallpaperManager: WallpaperManager,
    private val scope: CoroutineScope
) : CustomTarget<Bitmap>() {

    private var wallpaperSetJob: Job? = null

    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
        AppLogger.logInfo("onResourceReady", javaClass.simpleName)
        wallpaperSetJob = scope.launchOrInvoke(
            job = wallpaperSetJob,
            context = Dispatchers.IO,
            start = CoroutineStart.DEFAULT,
            block = {
                wallpaperManager.setBitmap(resource)
            }
        )
    }

    override fun onLoadCleared(placeholder: Drawable?) {
        AppLogger.logInfo("onLoadCleared", javaClass.simpleName)
        wallpaperSetJob?.cancel()
    }
}