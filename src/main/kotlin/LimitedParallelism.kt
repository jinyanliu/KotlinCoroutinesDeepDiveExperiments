package org.example

import kotlinx.coroutines.*
import kotlin.math.pow
import kotlin.time.measureTime

suspend fun main(): Unit {
    //defaultDispatcher()
    //defaultDispatcherOne()
    mainThread()
}

//86.373667ms
private suspend fun defaultDispatcher() {
    println(
        measureTime {
            coroutineScope {
                withContext(Dispatchers.Default) {
                    for (index in 1..1000) {
                        launch {
                            List(1000) { 1 }.maxOrNull()
                            //for (i in 0..10_000) 2f.pow(i)
                            //val threadName = Thread.currentThread().name
                            //println("Step $index running on thread: $threadName")
                        }
                    }
                }
            }
        }
    )
}

//48.161ms
@OptIn(ExperimentalCoroutinesApi::class)
private suspend fun defaultDispatcherOne() {
    println(
        measureTime {
            coroutineScope {
                withContext(Dispatchers.Default.limitedParallelism(1)) {
                    for (index in 1..1000) {
                        launch {
                            List(1000) { 1 }.maxOrNull()
                            //for (i in 0..10_000) 2f.pow(i)
                            //val threadName = Thread.currentThread().name
                            //println("Step $index running on thread: $threadName")
                        }
                    }
                }
            }
        }
    )
}

//100.704625ms
private suspend fun mainThread() {
    //println(Thread.currentThread().name)

    println(
        measureTime {
            coroutineScope {
                //println(Thread.currentThread().name)
                for (index in 1..1000) {
                    launch {
                        List(1000) { 1 }.maxOrNull()
                        //for (i in 0..10_000) 2f.pow(i)
                        //val threadName = Thread.currentThread().name
                        //println("Step $index running on thread: $threadName")
                    }
                }
            }
        }
    )
}


