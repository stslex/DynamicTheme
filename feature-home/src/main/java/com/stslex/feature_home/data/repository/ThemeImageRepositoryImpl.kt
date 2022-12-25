package com.stslex.feature_home.data.repository

import com.stslex.core.ListMapper
import com.stslex.core_data_source.dao.ThemeImageDao
import com.stslex.core_data_source.model.ThemeImageEntity
import com.stslex.core_data_source.model.ThemeTypeEntity
import com.stslex.feature_home.data.model.ThemeImageDataEntityMapper
import com.stslex.feature_home.data.model.ThemeImageDataModel
import com.stslex.feature_home.data.model.ThemeImageEntityDataMapper
import com.stslex.feature_home.domain.ThemeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeImageRepositoryImpl(
    private val themeImageDao: ThemeImageDao,
    private val mapperData: ThemeImageEntityDataMapper,
    private val mapperEntity: ThemeImageDataEntityMapper
) : ThemeImageRepository {

    override fun getAllThemeImage(): Flow<List<ThemeImageDataModel>> =
        themeImageDao.getAllThemeImage().map(ListMapper(mapperData)::invoke)

    override suspend fun setThemeImage(image: ThemeImageDataModel) {
        val imageEntity = mapperEntity(image)
        themeImageDao.setThemeImage(imageEntity)
    }

    override suspend fun deleteImage(type: ThemeType) {
        val entity = ThemeImageEntity(themeType = ThemeTypeEntity.getTypeOfValue(type.isDark))
        themeImageDao.setThemeImage(entity)
    }
}