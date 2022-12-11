package com.stslex.core_ui

import androidx.lifecycle.ViewModel
import com.stslex.core.AppLogger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.getScopeName

open class BaseViewModel : ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { context, e ->
        handleError(e = e, scope = context.getScopeName().value)
    }

    fun <T> Flow<T>.primaryFlow(): Flow<T> = catch { e ->
        handleError(e, getScopeName().value)
    }.flowOn(Dispatchers.IO)

    fun handleError(e: Throwable, scope: String = String()) {
        AppLogger.logException(
            throwable = e,
            scope = scope
        )
    }
}