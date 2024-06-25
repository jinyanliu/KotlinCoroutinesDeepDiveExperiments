package org.example

import kotlinx.coroutines.*
import kotlin.time.measureTime

suspend fun main(): Unit {
    //defaultDispatcher()
    //defaultDispatcherOne()
    //mainThread()
    //ioThread()
    //unconfined()
    //runBlockingThread()
    ioStartsRunBlocking()
}

//98.563209ms
private suspend fun defaultDispatcher() {
    println(Thread.currentThread().name)
    println(measureTime {
        coroutineScope {
            withContext(Dispatchers.Default) {
                for (index in 1..1000) {
                    launch {
                        println(Thread.currentThread().name)
                        List(1000) { 1 }.maxOrNull()
                    }
                }
            }
        }
    })
}

//53.522500ms
@OptIn(ExperimentalCoroutinesApi::class)
private suspend fun defaultDispatcherOne() {
    println(Thread.currentThread().name)
    println(
        measureTime {
            coroutineScope {
                withContext(Dispatchers.Default.limitedParallelism(1)) {
                    for (index in 1..1000) {
                        launch {
                            println(Thread.currentThread().name)
                            List(1000) { 1 }.maxOrNull()
                        }
                    }
                }
            }
        }
    )
}

//88.533792ms
private suspend fun mainThread() {
    println(Thread.currentThread().name)
    println(
        measureTime {
            coroutineScope {
                for (index in 1..1000) {
                    launch {
                        println(Thread.currentThread().name)
                        List(1000) { 1 }.maxOrNull()
                    }
                }
            }
        }
    )
}

//57.470417ms
private suspend fun ioThread() {
    println(Thread.currentThread().name)
    coroutineScope {
        withContext(Dispatchers.IO) {
            println(Thread.currentThread().name)
            println(
                measureTime {
                    coroutineScope {
                        for (index in 1..1000) {
                            launch {
                                println(Thread.currentThread().name)
                                List(1000) { 1 }.maxOrNull()
                            }
                        }
                    }
                }
            )
        }
    }
}

//48.182875ms
private suspend fun unconfined() {
    coroutineScope {
        withContext(Dispatchers.Unconfined) {
            println(Thread.currentThread().name)
            println(
                measureTime {
                    coroutineScope {
                        for (index in 1..1000) {
                            launch {
                                println(Thread.currentThread().name)
                                List(1000) { 1 }.maxOrNull()
                            }
                        }
                    }
                }
            )
        }
    }
}

//54.907334ms
private fun runBlockingThread() {
    println(Thread.currentThread().name)
    println(
        measureTime {
            runBlocking {
                for (index in 1..1000) {
                    launch {
                        println(Thread.currentThread().name)
                        List(1000) { 1 }.maxOrNull()
                    }
                }
            }
        }
    )
}

private suspend fun ioStartsRunBlocking() {
    println(Thread.currentThread().name)
    coroutineScope {
        withContext(Dispatchers.IO) {
            println(Thread.currentThread().name)
            println(
                measureTime {
                    runBlocking {
                        for (index in 1..1000) {
                            launch {
                                println(Thread.currentThread().name)
                                List(1000) { 1 }.maxOrNull()
                            }
                        }
                    }
                }
            )
        }
    }
}


