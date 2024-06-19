package org.example

import kotlinx.coroutines.*

suspend fun main() = withContext(Dispatchers.Main) {
    launch {
        delay(2000)
        println("Launch")
    }
    println("Yay")
}