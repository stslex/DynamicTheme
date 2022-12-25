package com.stslex.core

class ListMapper<in T, out U>(
    private val mapper: Mapper<T, U>
) : Mapper<List<T>, List<U>> {
    override fun invoke(data: List<T>): List<U> = data.map(mapper::invoke)
}