package com.stslex.feature_home.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ThemeImageDao {

    @Query("SELECT * FROM theme_image_table")
    fun getAllThemeImage(): Flow<List<ThemeImageEntity>>

    @Update
    suspend fun setThemeImage(image: ThemeImageEntity)

    @Insert
    suspend fun insertAll(list: List<ThemeImageEntity>)

    @Query("DELETE FROM theme_image_table")
    suspend fun deleteAll()
}