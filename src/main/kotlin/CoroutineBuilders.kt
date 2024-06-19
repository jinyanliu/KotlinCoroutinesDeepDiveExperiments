package org.example

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val parentJob = coroutineContext[Job]!!
        GlobalScope.launch(context = parentJob ) {
            delay(1000L)
            println("World!")
        }

        GlobalScope.launch(context = parentJob) {
            delay(2000L)
            println("World!")
        }
        println("Hello,")
    }
}
//Hello,

fun main34() {
    runBlocking {
        this.launch {
            delay(1000L)
            println("World!")
        }

        this.launch {
            delay(2000L)
            println("World!")
        }
        println("Hello,")
    }
}
//Hello,
//World!
//World!

fun main6() {
    runBlocking {
        coroutineScope {
            launch {
                delay(1000L)
                println("World!")
            }
        }
        coroutineScope {
            launch {
                delay(2000L)
                println("World!")
            }
        }
        println("Hello,")
    }
}
//World!
//World!
//Hello,

fun main78() {
    runBlocking {
        coroutineScope {
            launch {
                delay(1000L)
                println("World!")
            }
        }
        coroutineScope {
            launch {
                delay(2000L)
                println("World!")
            }
        }
        println("Hello,")
    }
}