package com.stslex.core_data_source.database

import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stslex.core_data_source.model.ThemeImageEntity
import com.stslex.core_data_source.model.ThemeTypeEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class AppDatabaseCallback : RoomDatabase.Callback() {

    private val roomDatabase: AppDatabase by inject(AppDatabase::class.java)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        Log.e(javaClass.simpleName, throwable.message, throwable.cause)
    }

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(SupervisorJob()).launch(
            Dispatchers.IO + exceptionHandler
        ) {
            roomDatabase.themeImageDao().apply {
                val values = ThemeTypeEntity.values().map { type -> ThemeImageEntity(type) }
                deleteAll()
                insertAll(values)
            }
        }
    }
}