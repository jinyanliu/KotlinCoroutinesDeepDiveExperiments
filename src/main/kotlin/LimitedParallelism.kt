package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.math.pow
import kotlin.time.measureTime

suspend fun main(): Unit {
    //defaultDispatcher()
    //defaultDispatcherOne()
    //mainThread()
    //ioThread()
    //unConfined()
    //emptyCoroutineContext()
    runBlockingThread()
}

//105.005459ms
private suspend fun defaultDispatcher() {
    println(Thread.currentThread().name)
    println(
        measureTime {
            coroutineScope {
                withContext(Dispatchers.Default) {
                    for (index in 1..1000) {
                        launch {
                            println(Thread.currentThread().name)
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

//50.189875ms
@OptIn(ExperimentalCoroutinesApi::class)
private suspend fun defaultDispatcherOne() {
    println(
        measureTime {
            coroutineScope {
                withContext(Dispatchers.Default.limitedParallelism(1)) {
                    for (index in 1..1000) {
                        launch {
                            println(Thread.currentThread().name)
                            List(1000) { 1 }.maxOrNull()
                            suspendFun()
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

private suspend fun suspendFun(){
    coroutineScope {
        println("haha" + Thread.currentThread().name)
        delay(1000)
    }
}

//94.752750ms
private suspend fun mainThread() {
    println(Thread.currentThread().name)

    println(
        measureTime {
            coroutineScope {
                for (index in 1..1000) {
                    launch {
                        println(Thread.currentThread().name)
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

//62.742875ms
private suspend fun ioThread() {
    coroutineScope {
        withContext(Dispatchers.IO) {
            //println(Thread.currentThread().name)
            println(
                measureTime {
                    coroutineScope {
                        for (index in 1..1000) {
                            launch {
                                println(Thread.currentThread().name)
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
    }
}

//43.635041ms
private suspend fun unconfined() {
    coroutineScope {
        withContext(Dispatchers.Unconfined) {
            //println(Thread.currentThread().name)
            println(
                measureTime {
                    coroutineScope {
                        for (index in 1..1000) {
                            launch {
                                println(Thread.currentThread().name)
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
    }
}

private suspend fun emptyCoroutineContext() {
    coroutineScope {
        withContext(EmptyCoroutineContext) {
            //println(Thread.currentThread().name)
            println(
                measureTime {
                    coroutineScope {
                        for (index in 1..1000) {
                            launch {
                                println(Thread.currentThread().name)
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
    }
}

private suspend fun runBlockingThread() {
    println(Thread.currentThread().name)

    println(
        measureTime {
            runBlocking {
                for (index in 1..1000) {
                    launch {
                        println(Thread.currentThread().name)
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

