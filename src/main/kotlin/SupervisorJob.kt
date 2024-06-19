package org.example

import kotlinx.coroutines.*

fun main344(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob())
    scope.launch {
        delay(1000)
        throw Error("Some error")
    }
    scope.launch {
        delay(2000)
        println("Will be printed")
    }
    delay(3000)
}

suspend fun main98766(): Unit = coroutineScope {
    val job = SupervisorJob()
    launch(job) {
        delay(1000)
        throw Error("Some error")
    }
    launch(job) {
        delay(2000)
        println("Will be printed")
    }
    delay(3000)
}

fun main(): Unit = runBlocking {
    supervisorScope {
        launch {
            delay(1000)
            throw Error("Some error")
        }
        launch {
            delay(2000)
            println("Will be printed")

            launch {
                delay(1000)
                throw Error("Some error 2")
            }

            launch {
                delay(2000)
                println("Will not be printed2")
            }
        }
    }
}

suspend fun main9877(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        delay(1000)
        throw CancellationException("Some error")
    }
    launch(job) {
        delay(2000)
        println("Will be printed")
    }
    delay(3000)
}

suspend fun main765654(): Unit = coroutineScope {
    val job = SupervisorJob()
    launch(job) {
        delay(1000)
        throw Exception("Error!")
    }
    launch(job) {
        delay(2000)
        println("Will be printed")
    }
    delay(3000)
}

suspend fun main67867865765(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            repeat(1000){i ->
                delay(200)
                println("Printing $i")
            }
        }catch (e: CancellationException){
            println(e)
            throw e
        }
    }
    delay(1100)
    job.cancelAndJoin()
    //
    println("Cancelled successfully")
    delay(1000)
}