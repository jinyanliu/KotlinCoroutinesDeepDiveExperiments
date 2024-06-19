package org.example

import kotlinx.coroutines.*

class MyException : Throwable()

suspend fun main3938487765() = coroutineScope {
    val str1 = async<String> {
        delay(1000)
        println("async1")
        throw MyException()
    }

    val str2 = async {
        delay(2000)
        println("async2")
        "Text2"
    }
}

suspend fun main():Unit = supervisorScope {
    launch {
        delay(1000)
        println("async1")
        throw MyException()
    }

    launch {
        delay(2000)
        println("async2")
    }
}