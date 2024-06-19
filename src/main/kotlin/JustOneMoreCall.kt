package org.example

import kotlinx.coroutines.*

suspend fun main(): Unit = coroutineScope {
    val job = Job()
    launch {
        delay(1000)
        job.cancelAndJoin()
    }
    withContext (job) {
        try {
            delay(2000)
            println("Job is done")
        } finally {
            println("Finally")

            val newJob = Job()
            launch (NonCancellable) {
                println("New job?")
            }

            launch {
                println("Will not be printed")
            }
            delay(1000)
            println("Will not be printed")
        }
    }
    println("Cancel done")
}