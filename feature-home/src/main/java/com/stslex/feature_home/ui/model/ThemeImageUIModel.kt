package com.stslex.feature_home.ui.model

import android.net.Uri

data class ThemeImageUIModel(
    val type: ThemeUIType = ThemeUIType.DARK,
    val uri: Uri = Uri.parse(String()),
    val isSelected: Boolean = false
) {

    fun onSelect(): ThemeImageUIModel = copy(
        isSelected = isSelected.not()
    )
}
