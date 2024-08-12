package org.example

import kotlinx.coroutines.*

suspend fun main(): Unit = coroutineScope {
    val job = SupervisorJob()
    launch(job) {
        delay(1000)
        println("text 1")
    }
    launch(job) {
        delay(2000)
        println("text 2")
    }
    job.join()
    println("Haha")
}