package com.stslex.dynamictheme

import android.app.Application
import android.content.Intent
import com.stslex.dynamictheme.di.appModules
import com.stslex.dynamictheme.reciever.ThemeChangeService
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
        startService(Intent(this, ThemeChangeService::class.java))
        super.onCreate()
    }
}