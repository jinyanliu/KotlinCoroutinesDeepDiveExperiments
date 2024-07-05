package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun CoroutineScope.produceString(
    s: String,
    time: Long
) = produce {
    while (true) {
        delay(time)
        send(s)
    }
}

fun main() = runBlocking {
    val fooChannel = produceString("foo", 210L)
    val barChannel = produceString("BAR", 500L)

    repeat(10) {
        select {
            fooChannel.onReceive {
                println("From fooChannel: $it")
            }
            barChannel.onReceive {
                println("From barChannel: $it")
            }
        }
    }

    coroutineContext.cancelChildren()
}