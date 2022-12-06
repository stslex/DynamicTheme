package com.stslex.feature_home.ui.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.domain.ThemeType
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.vm.HomeFeatureAbstractionViewModel
import com.stslex.feature_home.ui.vm.MockFeatureHomeViewModel

@Composable
fun HomeImagesSelectionBody(
    modifier: Modifier = Modifier,
    viewModel: HomeFeatureAbstractionViewModel
) {
    val isSystemDark = isSystemInDarkTheme()
    val isSelected = remember {
        mutableStateOf(isSystemDark)
    }

    var currentImageTypeClicked: ThemeType = ThemeType.DARK

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { result ->
            result?.let { url ->
                viewModel.pickImage(ThemeImageUIModel(currentImageTypeClicked, url))
            }
        }
    )

    val themeImageMap by remember(viewModel) {
        viewModel.themeImageListFlow
    }.collectAsState(null)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ThemeType.values().forEach { type ->
            themeImageMap?.get(type)?.let { image ->
                FeatureHomeImageSelectItem(
                    imageUIModel = image,
                    modifier = Modifier.weight(1f),
                    onImagePickClick = {
                        isSelected.value = isSelected.value.not()
                    },
                    isSelected = isSelected,
                    onChangeImageClick = {
                        currentImageTypeClicked = image.type
                        launcher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
                )
            }

        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_6", showBackground = true)
@Composable
fun HomeImagesSelectionBodyPreview() {
    val mockViewModel = MockFeatureHomeViewModel()
    AppTheme {
        HomeImagesSelectionBody(
            viewModel = mockViewModel
        )
    }
}