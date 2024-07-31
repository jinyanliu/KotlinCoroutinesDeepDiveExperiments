package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/*suspend fun main(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<String>(replay = 0)

    launch {
        mutableSharedFlow.collect{
            println("#1 received $it")
        }
    }

    launch {
        mutableSharedFlow.collect{
            println("#2 received $it")
        }
    }

    delay(1000)
    mutableSharedFlow.emit("Message1")
    mutableSharedFlow.emit("Message2")

    launch {
        mutableSharedFlow.collect{
            println("#3 received $it")
        }
    }
}*/

suspend fun main(): Unit = coroutineScope {
    val flow = flowOf("A", "B")

    launch {
        println("#1 ${flow.first()}")
        flow.collect{
            println("#1 received $it")
        }
    }

    launch {
        println("#2 ${flow.first()}")
        flow.collect{
            println("#2 received $it")
        }
    }

    delay(1000)

    launch {
        println("#3 ${flow.first()}")
        flow.collect{
            println("#3 received $it")
        }
    }
}