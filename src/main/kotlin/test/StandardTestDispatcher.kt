package org.example.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher

fun main() {
    //launching()
    //blocking()
    advanceTimeBy()
}

fun launching() {
    val testDispatcher = StandardTestDispatcher()
    CoroutineScope(testDispatcher).launch {
        println("Starting delay")
        delay(1)
        println("Ending delay")
    }
    testDispatcher.scheduler.advanceUntilIdle()
}

fun blocking() {
    val testDispatcher = StandardTestDispatcher()
    runBlocking(testDispatcher) {
        println("Starting delay")
        delay(1)
        println("Ending delay")
    }
    testDispatcher.scheduler.advanceUntilIdle()
}

fun advanceTimeBy(){
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

    for(i in 1..5){
        println(".")
        testDispatcher.scheduler.advanceTimeBy(1)
        testDispatcher.scheduler.runCurrent()
    }
}