package org.example

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.time.measureTime

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit {
    val time1 = measureTime {
        coroutineScope {
            for (index in 1..1000) {
                launch(Dispatchers.Default) {
                    List(1000) { 1 }.maxOrNull()
                    //val threadName = Thread.currentThread().name
                    //println("Step $index running on thread: $threadName")
                }
            }
        }
    }
    println("1, $time1")

    println("2, " +
            measureTime {
                coroutineScope {
                    for (index in 1..1000) {
                        launch(Dispatchers.Default.limitedParallelism(1)) {
                            List(1000) { 1 }.maxOrNull()
                            //val threadName = Thread.currentThread().name
                            //println("Step $index running on thread: $threadName")
                        }
                    }
                }
            }
    )

    println("3, " +
            measureTime {
                coroutineScope {
                    withContext(Dispatchers.Default) {
                        for (index in 1..1000) {
                            launch {
                                List(1000) { 1 }.maxOrNull()
                                //val threadName = Thread.currentThread().name
                                //println("Step $index running on thread: $threadName")
                            }
                        }
                    }
                }
            }
    )

    println("4, " +
            measureTime {
                coroutineScope {
                    withContext(Dispatchers.Default.limitedParallelism(1)) {
                        for (index in 1..1000) {
                            launch {
                                List(1000) { 1 }.maxOrNull()
                                //val threadName = Thread.currentThread().name
                                //println("Step $index running on thread: $threadName")
                            }
                        }
                    }
                }
            }
    )
}

suspend fun main9495857() = coroutineScope {
    withContext(Dispatchers.Default.limitedParallelism(1)) {
        val threadName = Thread.currentThread().name
        println(" running on thread: $threadName")
        println("Before")
        for (index in 1..1000) {
            launch {
                List(1000) { Random.nextLong() }.maxOrNull()
                val threadName = Thread.currentThread().name
                println("Step $index running on thread: $threadName")
            }
        }
        println("running on thread: $threadName")
        println("After")
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main758765486486(): Unit {
    val time1 = measureTime {
        coroutineScope {
            for (index in 1..1000) {
                launch(Dispatchers.Default) {
                    Thread.sleep(100)
                    //val threadName = Thread.currentThread().name
                    //println("Step $index running on thread: $threadName")
                }
            }
        }
    }
    println("1, $time1")

    println("2, " +
            measureTime {
                coroutineScope {
                    for (index in 1..1000) {
                        launch(Dispatchers.Default.limitedParallelism(1)) {
                            Thread.sleep(100)
                            //val threadName = Thread.currentThread().name
                            //println("Step $index running on thread: $threadName")
                        }
                    }
                }
            }
    )

    println("3, " +
            measureTime {
                coroutineScope {
                    withContext(Dispatchers.Default) {
                        for (index in 1..1000) {
                            launch {
                                Thread.sleep(100)
                                //val threadName = Thread.currentThread().name
                                //println("Step $index running on thread: $threadName")
                            }
                        }
                    }
                }
            }
    )

    println("4, " +
            measureTime {
                coroutineScope {
                    withContext(Dispatchers.Default.limitedParallelism(1)) {
                        for (index in 1..1000) {
                            launch {
                                Thread.sleep(100)
                                //val threadName = Thread.currentThread().name
                                //println("Step $index running on thread: $threadName")
                            }
                        }
                    }
                }
            }
    )
}