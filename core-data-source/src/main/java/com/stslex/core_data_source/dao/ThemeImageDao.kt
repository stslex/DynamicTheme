package com.stslex.core_data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stslex.core_data_source.model.ThemeImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThemeImageDao {

    @Query("SELECT * FROM theme_image_table")
    fun getAllThemeImage(): Flow<List<ThemeImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setThemeImage(image: ThemeImageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ThemeImageEntity>)

    @Query("DELETE FROM theme_image_table")
    suspend fun deleteAll()
}