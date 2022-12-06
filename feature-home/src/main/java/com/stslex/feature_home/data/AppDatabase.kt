package com.stslex.feature_home.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stslex.feature_home.data.data_source.ThemeImageDao
import com.stslex.feature_home.data.data_source.ThemeImageEntity

@Database(entities = [ThemeImageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun themeImageDao(): ThemeImageDao
}