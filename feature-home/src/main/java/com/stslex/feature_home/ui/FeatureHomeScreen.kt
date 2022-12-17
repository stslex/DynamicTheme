package com.stslex.feature_home.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.R
import com.stslex.feature_home.ui.components.HomeImagesSelectionBody
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import kotlinx.coroutines.flow.Flow

@Composable
fun FeatureHomeScreen(
    modifier: Modifier = Modifier,
    themeImageListFlow: () -> Flow<List<ThemeImageUIModel>>,
    onCardSelect: () -> Unit,
    onImageDeleteClicked: () -> Unit,
    onWallpaperSetClicked: () -> Unit,
    onImagePickClicked: () -> Unit
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item(key = "images_body") {
            HomeImagesSelectionBody(
                modifier = Modifier.fillMaxWidth(),
                themeImageListFlow = themeImageListFlow,
                onCardSelect = onCardSelect,
                onImageDeleteClicked = onImageDeleteClicked,
                onWallpaperSetClicked = onWallpaperSetClicked,
                onImagePickClicked = onImagePickClicked
            )
        }
        item(key = "text_body") {
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(32.dp),
                text = stringResource(id = R.string.feature_home_test_text),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(device = "id:pixel_6_pro", showSystemUi = true, showBackground = true)
@Composable
fun PreviewFeatureHomeScreen() {
    AppTheme {
    }
}