package com.stslex.core_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.AppDispatcher
import com.stslex.core.AppLogger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.getScopeName

open class BaseViewModel(
    private val dispatcher: AppDispatcher
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, e ->
        handleError(e = e, scope = context.getScopeName().value)
    }

    fun <T> Flow<T>.primaryFlow(): Flow<T> = catch { e ->
        handleError(e, getScopeName().value)
    }.flowOn(dispatcher.io)

    fun launchCoroutine(
        context: CoroutineDispatcher = dispatcher.io,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(
            context = context + coroutineExceptionHandler,
            start = start,
            block = block
        )
    }

    fun handleError(e: Throwable, scope: String = String()) {
        AppLogger.logException(
            throwable = e,
            scope = scope
        )
    }
}