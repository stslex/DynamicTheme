package com.stslex.core_data_source.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme_image_table")
data class ThemeImageEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "themeType")
    val themeType: ThemeTypeEntity,

    @ColumnInfo(name = "uri")
    val uri: String = ""
)