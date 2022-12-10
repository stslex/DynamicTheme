package com.stslex.feature_home.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType
import com.stslex.feature_home.ui.utils.ImagePicker
import com.stslex.feature_home.ui.vm.MockFeatureHomeViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.getScopeName

@Composable
fun HomeImagesSelectionBody(
    modifier: Modifier = Modifier,
    themeImageMapFlow: () -> Flow<Map<ThemeUIType, ThemeImageUIModel>>,
    imagePicker: () -> ImagePicker
) {
    val themeImageMap = remember {
        themeImageMapFlow()
    }.collectAsState(null)

    val isSelected = remember(LocalContext.getScopeName()) {
        mutableStateOf(false)
    }
    val picker = remember(LocalContext.getScopeName()) {
        imagePicker()
    }.invoke()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ThemeUIType.values().forEach { type ->
            themeImageMap.value?.get(type)?.let { image ->
                FeatureHomeImageSelectItem(
                    imageUIModel = image,
                    modifier = Modifier.weight(1f),
                    onImagePickClick = {
                        isSelected.value = isSelected.value.not()
                    },
                    isSelected = isSelected,
                    onChangeImageClick = {
                        picker.launch(image.type)
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
//        HomeImagesSelectionBody(
//            viewModel = mockViewModel
//        )
    }
}