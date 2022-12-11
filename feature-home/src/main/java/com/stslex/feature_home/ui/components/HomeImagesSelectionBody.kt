package com.stslex.feature_home.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeImagesSelectionBody(
    modifier: Modifier = Modifier,
    themeImageListFlow: () -> Flow<List<ThemeImageUIModel>>,
    onCardSelect: () -> Unit,
    onImageDeleteClicked: () -> Unit,
    onWallpaperSetClicked: () -> Unit,
    onImagePickClicked: () -> Unit
) {
    val themeImageMap = remember {
        themeImageListFlow()
    }.collectAsState(ThemeUIType.values().map { ThemeImageUIModel(it) })

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        themeImageMap.value.forEach { image ->
            FeatureHomeImageSelectItem(
                imageUIModel = image,
                modifier = Modifier.weight(1f),
                onImageSelect = onCardSelect,
                onImagePickClicked = onImagePickClicked,
                onImageDeleteClicked = onImageDeleteClicked,
                onWallpaperSetClicked = onWallpaperSetClicked
            )
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_6", showBackground = true)
@Composable
fun HomeImagesSelectionBodyPreview() {
    AppTheme {
//        HomeImagesSelectionBody(
//            viewModel = mockViewModel
//        )
    }
}