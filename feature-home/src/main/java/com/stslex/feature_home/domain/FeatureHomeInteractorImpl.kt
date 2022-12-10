package com.stslex.feature_home.domain

import com.stslex.feature_home.data.repository.ThemeImageRepository
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeatureHomeInteractorImpl(
    private val themeImageRepository: ThemeImageRepository
) : FeatureHomeInteractor {

    override fun getAllThemeImage(): Flow<Map<ThemeUIType, ThemeImageUIModel>> =
        themeImageRepository
            .getAllThemeImage().map { imageDataList ->
                imageDataList.map { imageData ->
                    imageData.mapToUI()
                }.associateBy { imageUI -> imageUI.type }
            }

    override suspend fun setThemeImage(image: ThemeImageUIModel) {
        val imageData = image.mapToData()
        themeImageRepository.setThemeImage(imageData)
    }
}