package com.stslex.feature_home.data.model

import android.net.Uri
import com.stslex.core_data_source.model.ThemeImageEntity
import com.stslex.core_data_source.model.ThemeTypeEntity
import com.stslex.feature_home.domain.ThemeType

fun ThemeImageDataModel.mapToEntity(): ThemeImageEntity = ThemeImageEntity(
    themeType = themeType.mapToEntity(),
    uri = uri.toString()
)

fun ThemeType.mapToEntity(): ThemeTypeEntity = ThemeTypeEntity.getTypeOfValue(isDark)

fun ThemeImageEntity.mapToData(): ThemeImageDataModel = ThemeImageDataModel(
    themeType = themeType.mapToData(),
    uri = Uri.parse(uri)
)

fun ThemeTypeEntity.mapToData(): ThemeType = ThemeType.getTypeOfValue(isDark)

