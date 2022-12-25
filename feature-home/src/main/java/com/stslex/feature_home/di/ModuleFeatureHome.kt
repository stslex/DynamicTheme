package com.stslex.feature_home.di

import com.stslex.feature_home.data.model.ThemeImageDataEntityMapper
import com.stslex.feature_home.data.model.ThemeImageEntityDataMapper
import com.stslex.feature_home.data.repository.ThemeImageRepository
import com.stslex.feature_home.data.repository.ThemeImageRepositoryImpl
import com.stslex.feature_home.domain.FeatureHomeInteractor
import com.stslex.feature_home.domain.FeatureHomeInteractorImpl
import com.stslex.feature_home.ui.FeatureHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class ModuleFeatureHome {
    val module = module {

        factoryOf(::FeatureHomeInteractorImpl) { bind<FeatureHomeInteractor>() }
        singleOf(::ThemeImageRepositoryImpl) { bind<ThemeImageRepository>() }
        factoryOf(ThemeImageDataEntityMapper::Base) { bind<ThemeImageDataEntityMapper>() }
        factoryOf(ThemeImageEntityDataMapper::Base) { bind<ThemeImageEntityDataMapper>() }
        viewModelOf(::FeatureHomeViewModel)
    }
}