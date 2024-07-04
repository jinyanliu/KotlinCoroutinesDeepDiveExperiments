package org.example.test

import kotlinx.coroutines.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

fun main() {
    launching()
    //blocking()
    //advanceTimeBy()
    //usingThread()
}

fun launching() =println(measureTime{
    val testDispatcher = StandardTestDispatcher()
    CoroutineScope(testDispatcher).launch {
        println("Starting delay")
        delay(1)
        println("Ending delay")
    }
    testDispatcher.scheduler.advanceUntilIdle()
})

fun blocking() {
    val testDispatcher = StandardTestDispatcher()
    runBlocking(testDispatcher) {
        println("Starting delay")
        delay(1)
        println("Ending delay")
    }
    testDispatcher.scheduler.advanceUntilIdle()
}

fun advanceTimeBy()= println(measureTime {
    val testDispatcher = StandardTestDispatcher()
    CoroutineScope(testDispatcher).launch {
        delay(2)
        print("Done")
    }
    CoroutineScope(testDispatcher).launch {
        delay(4)
        print("Done2")
    }
    CoroutineScope(testDispatcher).launch {
        delay(6)
        print("Done3")
    }

    Thread.sleep(2000)

    for (i in 1..5) {
        println(".")
        testDispatcher.scheduler.advanceTimeBy(1)
        testDispatcher.scheduler.runCurrent()
    }

    Thread.sleep(2000)
})

@OptIn(ExperimentalCoroutinesApi::class)
fun usingThread() = println(measureTime {
    val dispatcher = StandardTestDispatcher()

    CoroutineScope(dispatcher).launch {
        delay(1000)
        println("Coroutine done")
    }

    Thread.sleep(2000)

    val time = measureTime {
        println("[${dispatcher.scheduler.currentTime}] Before")
        dispatcher.scheduler.advanceUntilIdle()
        println("[${dispatcher.scheduler.currentTime}] After")
    }

    Thread.sleep(2000)
    println("Took $time")
})