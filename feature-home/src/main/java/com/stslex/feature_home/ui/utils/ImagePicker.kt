package com.stslex.feature_home.ui.utils

import androidx.compose.runtime.Composable
import com.stslex.feature_home.ui.model.ThemeUIType

interface ImagePicker {

    @Composable
    operator fun invoke(): ImagePicker

    fun launch(type: ThemeUIType)
}