package com.stslex.feature_home.data.model

import android.net.Uri
import com.stslex.feature_home.data.data_source.ThemeImageEntity
import com.stslex.feature_home.domain.ThemeType

fun ThemeImageDataModel.mapToEntity(): ThemeImageEntity = ThemeImageEntity(
    themeType.name,
    uri?.toString() ?: String()
)

fun ThemeImageEntity.mapToData(): ThemeImageDataModel = ThemeImageDataModel(
    ThemeType.valueOf(themeType),
    if (uri.isBlank()) {
        null
    } else {
        Uri.parse(uri)
    }
)
