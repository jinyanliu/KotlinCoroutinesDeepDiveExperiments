package org.example

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Before")
    withContext(SupervisorJob()){
        launch {
            delay(1000)
            throw Error()
        }

        launch {
            delay(2000)
            println("Done")
        }
    }

    launch {
        delay(1000)
        println("Haha")
    }
    println("After")

    while(true){
        yield()
    }
}