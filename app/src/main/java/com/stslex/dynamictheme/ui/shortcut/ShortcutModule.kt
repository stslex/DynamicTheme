package com.stslex.dynamictheme.ui.shortcut

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

class ShortcutModule {
    val module = module {
        single<ShortcutBuilder> {
            ShortcutBuilderImpl(
                context = androidApplication(),
            )
        }
    }
}