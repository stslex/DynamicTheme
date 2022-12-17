package com.stslex.feature_home.ui.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.R
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType

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
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                failure = {
                    Image(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                },
                requestOptions = {
                    RequestOptions()
                        .override(width, height)
                        .error(R.drawable.baseline_light_mode_24)
                        .downsample(DownsampleStrategy.FIT_CENTER)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSelectablePrimaryRowItem() {
    AppTheme {
        Row {
            FeatureHomeImageSelectCard(
                imageUIModel = ThemeImageUIModel(ThemeUIType.DARK, Uri.parse("")),
                onImagePickClick = {}
            )
        }
    }
}