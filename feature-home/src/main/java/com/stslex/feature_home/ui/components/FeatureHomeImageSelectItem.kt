package com.stslex.feature_home.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.R

@Composable
fun FeatureHomeImageSelectItem(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    isSelected: State<Boolean>,
    onImagePickClick: () -> Unit,
    onChangeImageClick: (Boolean) -> Unit,
    Content: @Composable (Boolean) -> Unit = {}
) {
    val animatedPadding = animateDpAsState(
        targetValue = if (isSelected.value == isDarkTheme) 16.dp else 32.dp
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.padding(animatedPadding.value),
            painter = painterResource(
                id = if (isDarkTheme) {
                    R.drawable.baseline_nightlight_24
                } else {
                    R.drawable.baseline_light_mode_24
                }
            ),
            contentDescription = if (isDarkTheme) {
                "night theme"
            } else {
                "light theme"
            }
        )
        FeatureHomeImageSelectCard(
            modifier = Modifier,
            isDarkTheme = isDarkTheme,
            Content = Content,
            onImagePickClick = onImagePickClick
        )
        AnimatedVisibility(
            visible = isSelected.value == isDarkTheme
        ) {
            OutlinedButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                onClick = {
                    onChangeImageClick(isDarkTheme)
                }
            ) {
                Text(text = "change")
            }
        }
    }
}

@Preview
@Composable
fun FeatureHomeImageSelectItemPreview() {
    AppTheme {
        FeatureHomeImageSelectCard(false, {})
    }
}