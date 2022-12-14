package com.stslex.dynamictheme.utils

object PrimaryExt {

    fun <T : Any> T?.nullIfSameOrNull(any: T?): T? =
        if (any == null || this == any) null else any
}