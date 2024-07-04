package org.example.test

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun main() = coroutineScope {
    runBlocking {
        delay(1000)
    }
    println("In the middle")
    runBlocking {
        delay(1000)
    }
}