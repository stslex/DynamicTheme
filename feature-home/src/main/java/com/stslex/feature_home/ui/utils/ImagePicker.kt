package com.stslex.feature_home.ui.utils

import android.app.Activity
import android.app.WallpaperManager.ACTION_CROP_AND_SET_WALLPAPER
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.stslex.core.AppLogger
import org.koin.core.component.getScopeName

class ImagePicker(
    private val onPickImage: (uri: Uri?) -> Unit,
) {

    private var _launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>? = null
    private val launcher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>
        get() = requireNotNull(_launcher)

    private var _wallpaperLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>? = null
    private val wallpaperLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>
        get() = requireNotNull(_wallpaperLauncher)

    @Composable
    operator fun invoke(): ImagePicker {
        _launcher = initPickImageLauncher()
        _wallpaperLauncher = initWallpaperLauncher()
        return this@ImagePicker
    }

    fun launchPickImage() {
        launcher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    fun launchSetWallpaper(uri: Uri?) {
        val currentUri = uri ?: return
        val intent = Intent(ACTION_CROP_AND_SET_WALLPAPER).apply {
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            setDataAndType(currentUri, MIME_IMAGE)
        }
        try {
            wallpaperLauncher.launch(intent)
        } catch (e: Throwable) {
            AppLogger.logException(e, "wallpaperLauncher")
        }
    }

    @Composable
    private fun initPickImageLauncher(): ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?> =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { result ->
                val uri = result ?: return@rememberLauncherForActivityResult
                onPickImage(uri)
            }
        )

    @Composable
    private fun initWallpaperLauncher() = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                AppLogger.logInfo("Wallpaper set success")
            } else {
                AppLogger.logException(
                    throwable = IllegalStateException(
                        "$ACTION_CROP_AND_SET_WALLPAPER ${result.resultCode} resultCode"
                    ),
                    scope = LocalContext.getScopeName().value
                )
            }
        }
    )

    companion object {
        private const val MIME_IMAGE = "image/*"
    }
}