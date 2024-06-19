package org.example

import kotlinx.coroutines.*

fun main8790786(): Unit = runBlocking {
    launch {
        try {
            delay(1000)
            throw Error("Some error")
        } catch (e: Throwable) {
            println("E caught: $e")
        }
    }
    launch {
        delay(2000)
        println("Will be printed")
    }
}

fun main68967656754(): Unit = runBlocking {
    try {
        launch {
            delay(1000)
            throw Error("Some error")
        }
    } catch (e: Throwable) {
        println("E caught: $e")
    }

    launch {
        delay(2000)
        println("Will not be printed")
    }
}

fun main3467364978(): Unit = runBlocking {
    supervisorScope {
        try {
            launch {
                delay(1000)
                throw Error("Some error")
            }
        } catch (e: Throwable) {
            println("E caught: $e")
        }

        launch {
            delay(2000)
            println("Will not be printed")
        }
    }
}

fun main67868769(): Unit = runBlocking {
    supervisorScope {
        try {
            async {
                delay(1000)
                throw Error("Some error")
            }.await()
        } catch (e: Throwable) {
            println("E caught: $e")
        }

        launch {
            delay(2000)
            println("Will not be printed")
        }
    }
}

fun main(): Unit = runBlocking {
    try {
        coroutineScope {
            delay(1000)
            throw Error("Some error")
        }
    } catch (e: Throwable) {
        println("E caught: $e")
    }

    launch {
        delay(2000)
        println("Will not be printed")
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main94858576(): Unit = runBlocking {
    val runBlockingJob = coroutineContext[Job]
    println("runBlockingJob=$runBlockingJob")
    try {
        coroutineScope {
            val coroutineScopeJob = coroutineContext[Job]
            println("coroutineScopeJob=$coroutineScopeJob")
            println("${coroutineScopeJob?.parent == runBlockingJob}")
            launch {
                delay(1000)
                throw Error("Some error")
            }
        }
    } catch (e: Throwable) {
        println("E caught: $e")
    }

    launch {
        delay(2000)
        println("Will be printed")
    }
}