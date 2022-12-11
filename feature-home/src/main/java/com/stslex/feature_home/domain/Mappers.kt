package com.stslex.feature_home.domain

import com.stslex.feature_home.data.model.ThemeImageDataModel
import com.stslex.feature_home.ui.model.ThemeImageUIModel
import com.stslex.feature_home.ui.model.ThemeUIType

fun ThemeImageDataModel.mapToUI(): ThemeImageUIModel = ThemeImageUIModel(
    type = themeType.mapToUI(),
    uri = uri,
    isSelected = themeType == ThemeType.DARK
)

fun ThemeImageUIModel.mapToData(): ThemeImageDataModel = ThemeImageDataModel(
    themeType = type.mapToData(),
    uri = uri
)

fun ThemeType.mapToUI(): ThemeUIType = ThemeUIType.getTypeOfValue(isDark)

fun ThemeUIType.mapToData(): ThemeType = ThemeType.getTypeOfValue(isDark)