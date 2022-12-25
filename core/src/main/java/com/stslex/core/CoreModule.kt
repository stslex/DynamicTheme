package com.stslex.core

import org.koin.dsl.module

class CoreModule {
    val module = module {
        single<AppDispatcher> {
            AppDispatcher.Base()
        }
    }
}