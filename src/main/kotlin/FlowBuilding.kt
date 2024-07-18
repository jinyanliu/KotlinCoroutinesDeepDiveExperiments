package org.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

suspend fun main() {
    val function = suspend {
        delay(1000)
        "UserName"
    }

    function.asFlow()
        .collect { println(it) }

    flow<String> {
        delay(1000)
        emit("UserName")
    }.collect { println(it) }
}