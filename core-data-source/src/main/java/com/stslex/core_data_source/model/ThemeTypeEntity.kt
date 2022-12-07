package com.stslex.core_data_source.model

enum class ThemeTypeEntity(val isDark: Boolean) {
    DARK(true),
    LIGHT(false);

    companion object {
        fun getTypeOfValue(value: Boolean) = values().first { it.isDark == value }
    }
}