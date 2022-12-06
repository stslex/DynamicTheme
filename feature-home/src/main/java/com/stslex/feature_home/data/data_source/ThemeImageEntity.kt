package com.stslex.feature_home.data.data_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stslex.feature_home.domain.ThemeType

@Entity(tableName = "theme_image_table")
data class ThemeImageEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "themeType")
    val themeType: String,

    @ColumnInfo(name = "uri")
    val uri: String = ""
)