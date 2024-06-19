package org.example

import kotlinx.coroutines.*
import kotlin.coroutines.cancellation.CancellationException

suspend fun test(): Int = withTimeout(1500) {
    delay(1000)
    println("Still thinking")
    delay(1000)
    println("Done!")
    42
}

suspend fun main343(): Unit = coroutineScope {
    try {
        test()
    } catch (e: TimeoutCancellationException) {
        println("Cancalled")
    }
    delay(1000)
    println("Done done")
}

object MyNonPropagatingException : CancellationException()

suspend fun main(): Unit = supervisorScope {
    withContext(CoroutineName("Haha")) {
        delay(1000)
        throw Error("My bad")
    }
    launch {
        delay(2000)
        println("Will be printed")
    }
}