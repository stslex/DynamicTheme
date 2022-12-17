package com.stslex.core_data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stslex.core_data_source.dao.ThemeImageDao
import com.stslex.core_data_source.model.ThemeImageEntity

@Database(
    entities = [ThemeImageEntity::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun themeImageDao(): ThemeImageDao
}