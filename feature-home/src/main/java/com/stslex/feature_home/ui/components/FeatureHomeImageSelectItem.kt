package com.stslex.feature_home.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.R
import com.stslex.feature_home.ui.model.ThemeImageUIModel

@Composable
fun FeatureHomeImageSelectItem(
    modifier: Modifier = Modifier,
    imageUIModel: ThemeImageUIModel,
    onImageSelect: () -> Unit,
    onImageDeleteClicked: () -> Unit,
    onWallpaperSetClicked: () -> Unit,
    onImagePickClicked: () -> Unit
) {
    val animatedPadding = animateDpAsState(
        targetValue = if (imageUIModel.isSelected) {
            16.dp
        } else {
            32.dp
        }
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.padding(animatedPadding.value),
            painter = painterResource(id = imageUIModel.type.iconRes),
            contentDescription = null
        )
        FeatureHomeImageSelectCard(
            modifier = Modifier,
            imageUIModel = imageUIModel,
            onImagePickClick = onImageSelect
        )
        AnimatedVisibility(
            visible = imageUIModel.isSelected
        ) {
            AppTheme(isDarkTheme = imageUIModel.type.isDark) {
                Row(
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    IconButton(
                        modifier = Modifier,
                        onClick = onImagePickClicked
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = null
                        )
                    }
                    IconButton(
                        modifier = Modifier,
                        onClick = onImageDeleteClicked,
                        enabled = imageUIModel.uri.toString().isNotBlank()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                    IconButton(
                        modifier = Modifier,
                        onClick = onWallpaperSetClicked,
                        enabled = imageUIModel.uri.toString().isNotBlank()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_wallpaper_24),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FeatureHomeImageSelectItemPreview() {
    AppTheme {
        val isSelected = remember {
            mutableStateOf(false)
        }
//        FeatureHomeImageSelectItem(
//            imageUIModel = ThemeImageUIModel(ThemeUIType.DARK, Uri.parse("")),
//            onImagePickClick = {},
//            onChangeImageClick = {}
//        )
    }
}