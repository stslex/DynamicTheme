package com.stslex.dynamictheme.ui.shortcut

import com.slex.dynamictheme.R
import com.stslex.core_data_source.model.ThemeTypeEntity

enum class Shortcut(
    val labelSource: Int,
    val shortLabelSource: Int,
    val longLabelSource: Int,
    val disabledMessageSource: Int,
    val iconSource: Int,
    val type: ThemeTypeEntity
) {
    DARK_WALLPAPER(
        labelSource = R.string.shortcut_night_label,
        shortLabelSource = R.string.shortcut_night_label_short,
        longLabelSource = R.string.shortcut_night_label_long,
        disabledMessageSource = R.string.shortcut_night_message_disable,
        iconSource = com.stslex.feature_home.R.drawable.baseline_nightlight_24,
        type = ThemeTypeEntity.DARK
    ),

    LIGHT_WALLPAPER(
        labelSource = R.string.shortcut_light_label,
        shortLabelSource = R.string.shortcut_light_label_short,
        longLabelSource = R.string.shortcut_light_label_long,
        disabledMessageSource = R.string.shortcut_light_message_disable,
        iconSource = com.stslex.feature_home.R.drawable.baseline_light_mode_24,
        type = ThemeTypeEntity.LIGHT
    )
}