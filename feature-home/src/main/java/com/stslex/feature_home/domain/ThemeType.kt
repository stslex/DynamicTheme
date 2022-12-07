package com.stslex.feature_home.domain

enum class ThemeType(val isDark: Boolean) {
    DARK(true),
    LIGHT(false);

    companion object {
        fun getTypeOfValue(value: Boolean) = ThemeType.values().first { it.isDark == value }
    }
}