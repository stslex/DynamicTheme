package com.stslex.feature_home.domain

import com.stslex.feature_home.ui.model.ThemeImageUIModel
import kotlinx.coroutines.flow.Flow

interface FeatureHomeInteractor {

    fun getAllThemeImage(): Flow<Map<ThemeType, ThemeImageUIModel>>

    suspend fun setThemeImage(image: ThemeImageUIModel)
}