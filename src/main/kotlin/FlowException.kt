package org.example

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class MyError : Throwable("My error")

val flow = flow {
    emit(1)
    throw MyError()
    emit(2)
}

suspend fun main(): Unit {
    flow.onEach { println("Got $it") }
        .catch { emit(3) }
        .onCompletion { println("On Completion") }
        .collect { println("Collected $it") }
}