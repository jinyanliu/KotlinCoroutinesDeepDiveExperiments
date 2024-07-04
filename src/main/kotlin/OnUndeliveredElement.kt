package org.example

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking


fun main() {
    val capacity = 2

    val channel = Channel<Int>(
        capacity = capacity,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        onUndeliveredElement = { value -> println("Dropped value: $value") }
    )

    runBlocking {
        (1..3).forEach { value ->
            channel.send(value)
            println("Sent value: $value")
        }
        channel.close()
    }
}