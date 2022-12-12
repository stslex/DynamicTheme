package com.stslex.dynamictheme.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

object CoroutineExt {

    fun CoroutineScope.launchOrInvoke(
        job: Job?,
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> Unit
    ): Job = job?.apply {
        invokeOnCompletion {
            launch(context, start, block)
        }
    } ?: launch(context, start, block)
}