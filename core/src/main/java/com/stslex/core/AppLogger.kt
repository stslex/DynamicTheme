package com.stslex.core

import android.util.Log

object AppLogger {

    fun logException(throwable: Throwable, scope: String = "") {
        Log.e(javaClass.simpleName, "$scope:${throwable.message}", throwable.cause)
    }

    fun logDebug(message: String, scope: String = "") {
        Log.d(javaClass.simpleName, "$scope:$message")
    }

    fun logInfo(message: String, scope: String = "") {
        Log.d(javaClass.simpleName, "$scope:$message")
    }
}