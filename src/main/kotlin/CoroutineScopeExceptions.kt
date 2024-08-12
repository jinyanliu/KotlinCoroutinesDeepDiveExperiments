package org.example

import kotlinx.coroutines.*

suspend fun main() = runBlocking {
    val scope = CoroutineScope(Job())
    scope.launch {
        delay(5000)
        println("Delayed 5000")
    }

    scope.launch {
        delay(4000)
        throw Exception("Cancelled")
    }

    delay(6000)
}