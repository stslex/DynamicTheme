package com.stslex.dynamictheme.reciever

import android.app.WallpaperManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

class ThemeServicesModule {
    val module = module {
        single {
            GlideReceiverCustomTarget.Factory(WallpaperManager.getInstance(androidApplication()))
        }
    }
}