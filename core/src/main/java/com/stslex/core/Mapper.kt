package com.stslex.core

fun interface Mapper<in T, out U> {
    operator fun invoke(data: T): U
}