package com.stslex.feature_home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.domain.ThemeType
import com.stslex.feature_home.ui.model.ThemeImageUIModel

@Composable
fun FeatureHomeImageSelectCard(
    modifier: Modifier = Modifier,
    imageUIModel: ThemeImageUIModel,
    onImagePickClick: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp
    val height = configuration.screenHeightDp
    AppTheme(isDarkTheme = imageUIModel.type.isDark) {
        ElevatedCard(
            modifier = modifier
                .padding(16.dp)
                .aspectRatio(0.7f),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            )
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds()
                    .clickable(onClick = onImagePickClick),
                imageModel = {
                    imageUIModel.uri
                },
                requestOptions = {
                    RequestOptions()
                        .override(width, height)
                        .downsample(DownsampleStrategy.FIT_CENTER)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSelectablePrimaryRowItem(
    modifier: Modifier = Modifier
) {
    AppTheme {
        Row {
            FeatureHomeImageSelectCard(
                imageUIModel = ThemeImageUIModel(ThemeType.DARK, null),
                onImagePickClick = {}
            )
        }
    }
}