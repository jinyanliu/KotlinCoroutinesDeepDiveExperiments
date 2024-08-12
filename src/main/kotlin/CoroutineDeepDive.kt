package org.example

import kotlinx.coroutines.*

suspend fun main(): Unit = coroutineScope {
    launch {
        delay(2000)
        println("Delayed 2000")
    }

    try {
        coroutineScope {
            launch {
                delay(1000)
                throw Exception("Error!")
            }
        }
    } catch (e: Exception) {
        println("Caught")
    }
}