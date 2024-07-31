package org.example

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

suspend fun main(): Unit {
    flowOf("Message 1")
        .catch { emit("Error") }
        .onEach { throw Error(it) }
        .collect {
            println("Collected $it")
        }
}