package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    main3()
}

private suspend fun main1(): Unit = coroutineScope {
    val flow = flowOf("A", "B", "C", "D")
        .onStart { println("Started") }
        .onCompletion { println("Finished") }
        .onEach { delay(1000) }

    val sharedFlow = flow.shareIn(
        scope = this,
        started = SharingStarted.WhileSubscribed()
    )

    delay(3000)
    launch {
        println("#1 ${sharedFlow.first()}")
    }
    launch {
        println("#2 ${sharedFlow.take(2).toList()}")
    }
    delay(3000)
    launch {
        println("#3 ${sharedFlow.first()}")
    }
}

private suspend fun main2(): Unit = coroutineScope {
    val flow = flowOf("A", "B", "C", "D")
        .onStart { println("Started") }
        .onCompletion { println("Finished") }
        .onEach { delay(1000) }

    val sharedFlow = flow.shareIn(
        scope = this,
        started = SharingStarted.WhileSubscribed()
    )

    delay(3000)
    launch {
        sharedFlow.collect { println("#1 collect $it") }
    }
    launch {
        sharedFlow.collect { println("#2 collect $it") }
    }
    delay(3000)
    launch {
        sharedFlow.collect { println("#3 collect $it") }
    }
}

private suspend fun main3(): Unit = coroutineScope {
    val flow = flowOf("A", "B", "C", "D")
        .onStart { println("Started") }
        .onCompletion { println("Finished") }
        .onEach { delay(1000) }

    val sharedFlow = flow.shareIn(
        scope = this,
        started = SharingStarted.WhileSubscribed()
    )

    delay(3000)
    launch {
        println("#1 ${sharedFlow.first()}")
        sharedFlow.collect { println("#1 collect $it") }
    }
    launch {
        println("#2 ${sharedFlow.take(2).toList()}")
        sharedFlow.collect { println("#2 collect $it") }
    }
    delay(3000)
    launch {
        println("#3 ${sharedFlow.first()}")
        sharedFlow.collect { println("#3 collect $it") }
    }
}