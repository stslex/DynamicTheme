package com.stslex.feature_home.ui.vm

import android.net.Uri
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType
import kotlinx.coroutines.flow.Flow

interface HomeFeatureAbstractionViewModel {

    val themeImageListFlow: Flow<Map<ThemeUIType, ThemeImageUIModel>>

    fun pickImage(type: ThemeUIType?, uri: Uri?)
}