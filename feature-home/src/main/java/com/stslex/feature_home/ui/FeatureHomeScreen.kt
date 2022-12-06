package com.stslex.feature_home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.core_ui.AppTheme
import com.stslex.feature_home.ui.components.HomeImagesSelectionBody
import com.stslex.feature_home.ui.components.HomeTitle
import com.stslex.feature_home.ui.vm.HomeFeatureAbstractionViewModel
import com.stslex.feature_home.ui.vm.MockFeatureHomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeatureHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeFeatureAbstractionViewModel = koinViewModel<FeatureHomeViewModel>()
) {
    Box(
        modifier = modifier,
    ) {
        HomeTitle()
        HomeImagesSelectionBody(
            modifier = Modifier.align(Alignment.Center),
            viewModel = viewModel
        )
    }
}


@Preview(device = "id:pixel_6_pro", showSystemUi = true, showBackground = true)
@Composable
fun PreviewFeatureHomeScreen() {
    val mockViewModel = MockFeatureHomeViewModel()
    AppTheme {
        FeatureHomeScreen(
            viewModel = mockViewModel
        )
    }
}