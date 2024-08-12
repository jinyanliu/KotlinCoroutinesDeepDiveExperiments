package org.example

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.measureTime

fun main(): Unit = println(measureTime {
    runBlocking {
        async {
            delay(1000)
        }
        async {
            delay(1000)
        }
    }
})