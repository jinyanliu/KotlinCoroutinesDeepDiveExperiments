package org.example

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun main08(): Unit = coroutineScope {
    val job = launch {
        repeat(1000){i ->
            delay(100)
            Thread.sleep(100)
            println("Printing $i")
        }
    }

    delay(1000)
    job.cancelAndJoin()
    println("Cancelled successfully")
}

suspend fun main46(): Unit = coroutineScope {
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
    delay(5000)
}

suspend fun main72(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            delay(Random.nextLong(2000))
            println("Done")
            println("Done job =$job")
        } finally {
            try {
                println("Will always be printed")
                println("Done job2 =$job")
                delay(4000)
            }catch (e: CancellationException){
                println(e)
                throw e
            }

        }
    }
    delay(1000)
    println("Done job3 =$job")
    job.cancelAndJoin()
    println(job)
}

suspend fun main67(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            delay(2000)
            println("Job is done")
        } finally {
            println("Finally")
            launch {
                println("Will not be printed")
            }
            delay(1000)
            println("Will not be printed")
        }
    }
    delay(1000)
    job.cancelAndJoin()
    println("Cancel done")
}

suspend fun main456(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            withContext(NonCancellable){
                delay(2000)
                println("non cancallable try")
            }
            delay(2000)
            println("Coroutine finished")
        } finally {
            println("Finally")
            withContext(NonCancellable){
                delay(1000)
                println("Cleanup done")
            }
        }
    }
    delay(1000)
    job.cancelAndJoin()
    println("done")
}

suspend fun main(): Unit = coroutineScope {
    val job = coroutineContext[Job]
    launch{
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
    job?.cancelAndJoin()
    println("Cancelled successfully")
    delay(5000)
}

