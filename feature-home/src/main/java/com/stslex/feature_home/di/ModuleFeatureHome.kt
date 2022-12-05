package com.stslex.feature_home.di

import com.stslex.feature_home.ui.FeatureHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

class ModuleFeatureHome {
    val module = module {
        viewModelOf(::FeatureHomeViewModel)
    }
}