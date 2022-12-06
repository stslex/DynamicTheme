package com.stslex.dynamictheme

import android.app.Application
import com.stslex.dynamictheme.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DynamicThemeApp : Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@DynamicThemeApp)
            modules(appModules)
        }
        super.onCreate()
    }
}