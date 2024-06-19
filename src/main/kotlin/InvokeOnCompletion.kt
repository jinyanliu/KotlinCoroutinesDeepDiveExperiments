package org.example

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = launch {
        delay(1000)
        println("job done")
        throw Error("Big Error!")
    }

    job.invokeOnCompletion {exception: Throwable? ->
        println("Finished")
        println(exception)
    }

    //delay(400)
    //job.cancelAndJoin()
}