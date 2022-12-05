package com.stslex.feature_home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.core_ui.AppTheme

@Composable
fun FeatureHomeImageSelectCard(
    isDarkTheme: Boolean,
    onImagePickClick: () -> Unit,
    modifier: Modifier = Modifier,
    Content: @Composable (Boolean) -> Unit = {}
) {
    AppTheme(isDarkTheme = isDarkTheme) {
        ElevatedCard(
            modifier = modifier
                .padding(16.dp)
                .aspectRatio(0.7f),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = onImagePickClick)
            ) {
                Content(isDarkTheme)
            }
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
            FeatureHomeImageSelectCard(false, {})
        }
    }
}