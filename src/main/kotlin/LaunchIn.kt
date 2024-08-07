package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

suspend fun main():Unit = coroutineScope {
    main5()
}
private suspend fun main1(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<String>(replay = 0)
    mutableSharedFlow.onEach { println("She said $it") }.launchIn(this)
    delay(1)
    mutableSharedFlow.emit("Hi")
}

private suspend fun main2(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<String>()
    launch {
        mutableSharedFlow.collect{
            println("She said $it")
        }
    }
    delay(1)
    mutableSharedFlow.emit("Hi")
}

private suspend fun main3(): Unit = coroutineScope {
    val flow = flowOf("A")
    val mutableSharedFlow = flow.shareIn(this, started = SharingStarted.Lazily)
    mutableSharedFlow.onEach { println("She said $it") }.launchIn(this)
}

private suspend fun main4(): Unit = coroutineScope {
    val flow = flowOf("A")
    val mutableSharedFlow = flow.shareIn(this, started = SharingStarted.Lazily)
    mutableSharedFlow.onEach { println("She said $it") }.first()
}

private suspend fun main5(): Unit = coroutineScope {
    val flow = flowOf("A")
    val mutableSharedFlow = flow.shareIn(this, started = SharingStarted.Lazily)
    mutableSharedFlow.onEach { println("She said $it") }.take(1).first()
}