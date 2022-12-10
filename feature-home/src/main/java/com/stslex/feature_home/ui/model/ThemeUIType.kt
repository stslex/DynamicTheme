package com.stslex.feature_home.ui.model

import com.stslex.feature_home.R

enum class ThemeUIType(
    val isDark: Boolean,
    val iconRes: Int
) {
    DARK(true, R.drawable.baseline_nightlight_24),
    LIGHT(false, R.drawable.baseline_light_mode_24);

    companion object {
        fun getTypeOfValue(value: Boolean) = ThemeUIType.values().first { it.isDark == value }
    }
}