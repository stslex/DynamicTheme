package com.stslex.feature_home.ui.components

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType

@Composable
fun FeatureHomeImageSelectItem(
    modifier: Modifier = Modifier,
    imageUIModel: ThemeImageUIModel,
    isSelected: State<Boolean>,
    onImagePickClick: () -> Unit,
    onChangeImageClick: () -> Unit,
) {
    val animatedPadding = animateDpAsState(
        targetValue = if (isSelected.value == imageUIModel.type.isDark) {
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
            onImagePickClick = onImagePickClick
        )
        AnimatedVisibility(
            visible = isSelected.value == imageUIModel.type.isDark
        ) {
            OutlinedButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                onClick = onChangeImageClick
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
        val isSelected = remember {
            mutableStateOf(false)
        }
        FeatureHomeImageSelectItem(
            imageUIModel = ThemeImageUIModel(ThemeUIType.DARK, Uri.parse("")),
            isSelected = isSelected,
            onImagePickClick = {},
            onChangeImageClick = {}
        )
    }
}