package com.stslex.feature_home.domain

import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType
import kotlinx.coroutines.flow.Flow

interface FeatureHomeInteractor {

    fun getAllThemeImage(): Flow<List<ThemeImageUIModel>>

    suspend fun setThemeImage(image: ThemeImageUIModel)

    suspend fun deleteImage(type: ThemeUIType)
}