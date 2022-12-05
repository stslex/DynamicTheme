package com.stslex.feature_home.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.core_ui.AppTheme

@Composable
fun HomeImagesSelectionBody(
    modifier: Modifier = Modifier,
    onChangeImageClick: (Boolean) -> Unit,
    Content: @Composable (Boolean) -> Unit
) {
    val isSystemDark = isSystemInDarkTheme()
    val isSelected = remember {
        mutableStateOf(isSystemDark)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FeatureHomeImageSelectItem(
            isDarkTheme = false,
            modifier = Modifier.weight(1f),
            onImagePickClick = {
                isSelected.value = isSelected.value.not()
            },
            isSelected = isSelected,
            onChangeImageClick = onChangeImageClick,
            Content = Content
        )
        FeatureHomeImageSelectItem(
            isDarkTheme = true,
            modifier = Modifier.weight(1f),
            onImagePickClick = {
                isSelected.value = isSelected.value.not()
            },
            isSelected = isSelected,
            onChangeImageClick = onChangeImageClick,
            Content = Content
        )
    }
}

@Preview(showSystemUi = true, device = "id:pixel_6", showBackground = true)
@Composable
fun HomeImagesSelectionBodyPreview() {
    AppTheme() {
        HomeImagesSelectionBody(onChangeImageClick = {}, Content = {})
    }
}