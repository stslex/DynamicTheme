package com.stslex.core_data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stslex.core_data_source.model.ThemeImageEntity
import com.stslex.core_data_source.model.ThemeTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThemeImageDao {

    @Query("SELECT * FROM theme_image_table")
    fun getAllThemeImage(): Flow<List<ThemeImageEntity>>

    @Query("SELECT uri FROM theme_image_table WHERE themeType=:type")
    suspend fun getImageUri(type: ThemeTypeEntity): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setThemeImage(image: ThemeImageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ThemeImageEntity>)

    @Query("DELETE FROM theme_image_table")
    suspend fun deleteAll()
}