package com.stslex.feature_home.data.repository

import com.stslex.feature_home.data.model.ThemeImageDataModel
import kotlinx.coroutines.flow.Flow

interface ThemeImageRepository {

    fun getAllThemeImage(): Flow<List<ThemeImageDataModel>>

    suspend fun setThemeImage(image: ThemeImageDataModel)
}