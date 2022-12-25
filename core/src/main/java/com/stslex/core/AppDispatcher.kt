package com.stslex.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatcher {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher

    data class Base(
        override val io: CoroutineDispatcher = Dispatchers.IO,
        override val main: CoroutineDispatcher = Dispatchers.Main,
        override val default: CoroutineDispatcher = Dispatchers.Default
    ) : AppDispatcher
}