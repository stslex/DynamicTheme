package com.stslex.dynamictheme.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stslex.core_navigation.AppDestination
import com.stslex.feature_home.navigation.featureHomaGraph

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: AppDestination = AppDestination.HOME
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.navigationRoute,
        builder = {
            featureHomaGraph(modifier = modifier)
        }
    )
}