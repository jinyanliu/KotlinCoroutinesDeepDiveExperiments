package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

suspend fun main(): Unit = coroutineScope {
    flow {
        flowOf(1, 2, 3).collect(this)
    }.collect { println(it) }
}