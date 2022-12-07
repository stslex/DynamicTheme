package com.stslex.core_data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.stslex.core_data_source.model.ThemeImageEntity
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