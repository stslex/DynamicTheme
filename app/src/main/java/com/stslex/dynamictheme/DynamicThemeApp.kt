package com.stslex.dynamictheme

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import com.stslex.dynamictheme.di.appModules
import com.stslex.dynamictheme.reciever.ThemeChangeReceiver
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
        registerReceiver(
            ThemeChangeReceiver(),
            IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED)
        )
        super.onCreate()
    }
}