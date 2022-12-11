package com.stslex.feature_home.ui.utils

import com.stslex.feature_home.ui.model.ThemeImageUIModel

fun List<ThemeImageUIModel>.mapUriByType(
    otherList: List<ThemeImageUIModel>
) = map { value ->
    val imageUri = otherList.firstOrNull { it.type == value.type }?.uri
    value.copy(uri = imageUri ?: value.uri)
}
