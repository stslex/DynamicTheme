package com.stslex.feature_home.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.stslex.feature_home.data.AppDatabase
import com.stslex.feature_home.data.AppDatabaseCallback
import com.stslex.feature_home.data.repository.ThemeImageRepository
import com.stslex.feature_home.data.repository.ThemeImageRepositoryImpl
import com.stslex.feature_home.domain.FeatureHomeInteractor
import com.stslex.feature_home.domain.FeatureHomeInteractorImpl
import com.stslex.feature_home.ui.FeatureHomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class ModuleFeatureHome {
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

        factoryOf(::FeatureHomeInteractorImpl) { bind<FeatureHomeInteractor>() }

        singleOf(::ThemeImageRepositoryImpl) { bind<ThemeImageRepository>() }

        viewModelOf(::FeatureHomeViewModel)
    }
}