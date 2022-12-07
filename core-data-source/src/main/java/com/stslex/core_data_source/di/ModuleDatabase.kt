package com.stslex.core_data_source.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.stslex.core_data_source.database.AppDatabase
import com.stslex.core_data_source.database.AppDatabaseCallback
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class ModuleDatabase {

    val module = module {

        single {
            Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                AppDatabase::class.java.simpleName
            ).addCallback(get()).build()
        }

        singleOf<RoomDatabase.Callback>(::AppDatabaseCallback)

        single {
            get<AppDatabase>().themeImageDao()
        }
    }
}