package com.stslex.feature_home.data.repository

import com.stslex.feature_home.data.model.ThemeImageDataModel
import com.stslex.feature_home.domain.ThemeType
import kotlinx.coroutines.flow.Flow

interface ThemeImageRepository {

    fun getAllThemeImage(): Flow<List<ThemeImageDataModel>>

    suspend fun setThemeImage(image: ThemeImageDataModel)

    suspend fun deleteImage(type: ThemeType)
}