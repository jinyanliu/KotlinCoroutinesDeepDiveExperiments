package org.example.test

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext

suspend fun main(): Unit = coroutineScope {
    listOf("A").mapAsync {
        println(currentCoroutineContext())
        println(coroutineContext)
        it
    }
}

suspend fun <T, R> Iterable<T>.mapAsync(
    transformation: suspend (T) -> R
): List<R> = coroutineScope {
    this@mapAsync.map {
        async {
            transformation(it)
        }
    }.awaitAll()
}


