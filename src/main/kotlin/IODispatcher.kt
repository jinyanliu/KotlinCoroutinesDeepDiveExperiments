package org.example

import kotlinx.coroutines.*
import kotlin.time.measureTime

suspend fun main217348963874() {
    val time = measureTime {
        coroutineScope {
            for (index in 1..70) {
                launch(Dispatchers.Default) {
                    List(1000) { 1 }.sorted()
                    withContext(Dispatchers.IO) {
                        Thread.sleep(1000)
                    }
                }
            }
        }
    }
    println(time)
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    val time = measureTime {
        coroutineScope {
            repeat(100) {
                launch (Dispatchers.Default.limitedParallelism(100)){
                    delay(1000)
                    val threadName = Thread.currentThread().name
                    println("threadName=$threadName")
                }
            }
        }
    }
    println(time)

    val time2 = measureTime {
        coroutineScope {
            repeat(100) {
                launch {
                    withContext(Dispatchers.IO.limitedParallelism(100)){ Thread.sleep(1000)}
                    val threadName = Thread.currentThread().name
                    println("threadName=$threadName")
                }
            }
        }
    }
    println(time2)
}