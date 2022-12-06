package com.stslex.feature_home.ui.vm

import com.stslex.feature_home.domain.ThemeType
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface HomeFeatureAbstractionViewModel {

    val themeImageListFlow: StateFlow<Map<ThemeType, ThemeImageUIModel>?>

    fun pickImage(image: ThemeImageUIModel)
}