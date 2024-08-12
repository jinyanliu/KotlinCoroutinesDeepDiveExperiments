package org.example

import kotlinx.coroutines.*

suspend fun main(): Unit = main4()

private suspend fun main1(): Unit = supervisorScope {
    launch {
        delay(2000)
        println("I am the parent")
    }
    try {
        //Doesn't rethrow, silencing the exception
        //Try catch can be removed, see main2()
        supervisorScope {
            launch {
                delay(1000)
                throw Exception("Exception")
            }
        }
    } catch (e: Exception) {
        println("Caught!")
    }
}

private suspend fun main2(): Unit = coroutineScope {
    launch {
        delay(2000)
        println("I am the parent")
    }
    //Doesn't rethrow, silencing the exception
    supervisorScope {
        launch {
            delay(1000)
            throw Exception("Exception")
        }
    }
}

private suspend fun main3(): Unit = supervisorScope {
    launch {
        delay(2000)
        println("I am the parent")
    }
    try {
        //Rethrowed
        //If removing try catch, process stops.
        coroutineScope {
            launch {
                delay(1000)
                throw Exception("Exception")
            }
        }
    } catch (e: Exception) {
        println("Caught!")
    }
}

private suspend fun main4(): Unit = supervisorScope {
    launch {
        delay(2000)
        println("I am the parent")
    }
    //Rethrowed
    //If removing try catch, process stops.
    coroutineScope {
        launch {
            delay(1000)
            throw Exception("Exception")
        }
    }
}

private suspend fun main5(): Unit = supervisorScope {
    launch {
        delay(2000)
        println("I am the parent")
    }
    launch {
        delay(1000)
        throw Exception("Exception")
    }
}

private suspend fun main7(): Unit = coroutineScope {
    launch {
        delay(2000)
        println("I am the parent")
    }
    launch {
        delay(1000)
        throw Exception("Exception")
    }
}