package com.stslex.feature_home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.core_navigation.AppDestination
import com.stslex.feature_home.ui.FeatureHomeRoute

fun NavGraphBuilder.featureHomaGraph(modifier: Modifier = Modifier) {
    composable(AppDestination.HOME.navigationRoute) {
        FeatureHomeRoute(modifier = modifier)
    }
}