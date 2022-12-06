package com.stslex.feature_home.data.repository

import com.stslex.feature_home.data.data_source.ThemeImageDao
import com.stslex.feature_home.data.model.ThemeImageDataModel
import com.stslex.feature_home.data.model.mapToData
import com.stslex.feature_home.data.model.mapToEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeImageRepositoryImpl(
    private val themeImageDao: ThemeImageDao
) : ThemeImageRepository {

    override fun getAllThemeImage(): Flow<List<ThemeImageDataModel>> =
        themeImageDao.getAllThemeImage().map { imageEntityList ->
            imageEntityList.map { imageEntity -> imageEntity.mapToData() }
        }

    override suspend fun setThemeImage(image: ThemeImageDataModel) {
        val imageEntity = image.mapToEntity()
        themeImageDao.setThemeImage(imageEntity)
    }

}