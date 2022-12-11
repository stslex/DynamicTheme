package com.stslex.feature_home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.stslex.feature_home.ui.utils.ImagePicker
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeatureHomeRoute(
    modifier: Modifier = Modifier,
    viewModel: FeatureHomeViewModel = koinViewModel()
) {
    val imagePicker = remember(viewModel) {
        ImagePicker(viewModel::onImagePicked)
    }.invoke()
    FeatureHomeScreen(
        modifier = modifier,
        themeImageListFlow = viewModel::themeImageListFlow,
        onCardSelect = viewModel::onCardSelect,
        onImageDeleteClicked = viewModel::onImageDeleteClicked,
        onWallpaperSetClicked = {
            imagePicker.launchSetWallpaper(viewModel.selectedImage?.uri)
        },
        onImagePickClicked = imagePicker::launchPickImage
    )
}