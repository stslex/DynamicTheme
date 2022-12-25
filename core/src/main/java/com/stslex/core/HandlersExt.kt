package com.stslex.core

import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.core.component.getScopeName

object HandlersExt {
    val coroutineHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        AppLogger.logException(throwable, coroutineContext.getScopeName().value)
    }
}