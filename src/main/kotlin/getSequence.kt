package org.example

import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun main(){
    withContext(newSingleThreadContext("main")){
        launch {
            delay(5000)
            val thread = Thread.currentThread().name
            println("Hello1 $thread")
        }
        launch {
            delay(5000)
            val thread = Thread.currentThread().name
            println("Hello2 $thread")
        }
        launch {
            delay(5000)
            val thread = Thread.currentThread().name
            println("Hello3 $thread")
        }
    }
}