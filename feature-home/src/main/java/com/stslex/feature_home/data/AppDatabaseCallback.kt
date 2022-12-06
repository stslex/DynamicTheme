package com.stslex.feature_home.data

import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stslex.feature_home.data.data_source.ThemeImageEntity
import com.stslex.feature_home.domain.ThemeType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class AppDatabaseCallback : RoomDatabase.Callback() {

    private val roomDatabase: AppDatabase by KoinJavaComponent.inject(AppDatabase::class.java)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        Log.e(javaClass.simpleName, throwable.message, throwable.cause)
    }

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Log.i(javaClass.simpleName, "onCreate")
        CoroutineScope(SupervisorJob()).launch(
            Dispatchers.IO + exceptionHandler
        ) {
            roomDatabase.themeImageDao().apply {
                val values = ThemeType.values().map { type -> ThemeImageEntity(type.name) }
                deleteAll()
                insertAll(values)
            }
        }
    }
}