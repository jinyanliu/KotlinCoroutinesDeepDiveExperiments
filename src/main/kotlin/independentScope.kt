package org.example

import kotlinx.coroutines.*
import kotlin.random.Random

val myScope = CoroutineScope(SupervisorJob())

suspend fun main(): Unit = coroutineScope {

    val number = async {
        delay(1000)
        List(1000){ Random.nextLong()}
        24
    }
    println(number.await())

    launch {
        throw Error("Check!")
    }

    launch {
        delay(1000)
        println("Done")
    }
}



