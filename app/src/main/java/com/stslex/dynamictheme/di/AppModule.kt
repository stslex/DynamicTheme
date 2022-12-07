package com.stslex.dynamictheme.di

import com.stslex.core_data_source.di.ModuleDatabase
import com.stslex.feature_home.di.ModuleFeatureHome

val appModules = listOf(
    ModuleFeatureHome().module,
    ModuleDatabase().module
)