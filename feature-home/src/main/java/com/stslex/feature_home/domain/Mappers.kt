package com.stslex.feature_home.domain

import com.stslex.feature_home.data.model.ThemeImageDataModel
import com.stslex.feature_home.ui.model.ThemeImageUIModel

fun ThemeImageDataModel.mapToUI(): ThemeImageUIModel = ThemeImageUIModel(themeType, uri)

fun ThemeImageUIModel.mapToData(): ThemeImageDataModel = ThemeImageDataModel(type, uri)