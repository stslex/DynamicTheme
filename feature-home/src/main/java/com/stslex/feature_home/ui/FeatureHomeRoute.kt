package com.stslex.feature_home.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeatureHomeRoute(
    modifier: Modifier = Modifier,
    viewModel: FeatureHomeViewModel = koinViewModel()
) {
    FeatureHomeScreen(modifier = modifier)
}