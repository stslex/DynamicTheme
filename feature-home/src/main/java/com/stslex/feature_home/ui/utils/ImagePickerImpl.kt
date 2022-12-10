package com.stslex.feature_home.ui.utils

import android.app.Activity
import android.app.WallpaperManager
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
import com.stslex.feature_home.ui.model.ThemeUIType
import org.koin.core.component.getScopeName

class ImagePickerImpl(
    private val onPickImage: (type: ThemeUIType?, uri: Uri?) -> Unit
) : ImagePicker {

    private var typeClicked: ThemeUIType? = null
    private var uriClicked: Uri? = null
    private var pickImageLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>? =
        null
    private var wallpaperLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>? = null

    @Composable
    override operator fun invoke(): ImagePicker {
        InitPickImageLauncher()
        InitWallpaperLauncher()
        return this@ImagePickerImpl
    }

    override fun launch(type: ThemeUIType) {
        this.typeClicked = type
        pickImageLauncher?.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }

    @Composable
    private fun InitPickImageLauncher() {
        pickImageLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { result ->
                uriClicked = result ?: return@rememberLauncherForActivityResult
                val intent = Intent(WallpaperManager.ACTION_CROP_AND_SET_WALLPAPER).apply {
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    setDataAndType(uriClicked, "image/*")
                }
                try {
                    wallpaperLauncher?.launch(intent)
                } catch (e: Throwable) {
                    AppLogger.logException(e, "ACTION_CROP_AND_SET_WALLPAPER launch error")
                }
            }
        )
    }

    @Composable
    private fun InitWallpaperLauncher() {
        wallpaperLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    onPickImage(typeClicked, uriClicked)
                } else {
                    val throwable =
                        IllegalStateException("ACTION_CROP_AND_SET_WALLPAPER ${result.resultCode} resultCode")
                    AppLogger.logException(
                        throwable = throwable,
                        scope = LocalContext.getScopeName().value
                    )
                }
            }
        )
    }
}