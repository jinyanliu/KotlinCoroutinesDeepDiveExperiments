package org.example

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val handler =
        CoroutineExceptionHandler{coroutineContext, throwable ->
            println("Caught $throwable")
        }
    val scope = CoroutineScope(SupervisorJob() + handler)
    scope.launch {
        delay(1000)
        throw Error("Some error")
    }
    scope.launch {
        delay(2000)
        throw Error("Some error 2")
    }

    delay(3000)
}