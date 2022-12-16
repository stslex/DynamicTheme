package com.stslex.dynamictheme.ui.widget

import kotlinx.serialization.Serializable

@Serializable
sealed interface ThemeWidgetState {

    @Serializable
    object Loading : ThemeWidgetState

    @Serializable
    object Failed : ThemeWidgetState

    @Serializable
    object Success : ThemeWidgetState
}