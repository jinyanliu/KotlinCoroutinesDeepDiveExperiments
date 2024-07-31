package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    main3()
}

//A emitted
//A
//B emitted
//B
//C emitted
//C
//D emitted
//D
//E emitted
//E
//Start listening
private suspend fun main1(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<Char>(replay = 0)

    launch {
        for (c in 'A'..'E') {
            delay(300)
            println("$c emitted")
            mutableSharedFlow.emit(c)
        }
    }

    launch {
        mutableSharedFlow.collect {
            println(it)
        }
    }

    delay(2000)

    launch {
        println("Start listening")
        mutableSharedFlow.collect {
            delay(1000)
            println("second $it")
        }
    }
}

//A emitted
//B emitted
//A
//C emitted
//Start listening
//B
//D emitted
//second C
//C
//E emitted
//second D
//D
//second E
//E
private suspend fun main2(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<Char>(replay = 0)

    launch {
        for (c in 'A'..'E') {
            delay(300)
            println("$c emitted")
            mutableSharedFlow.emit(c)
        }
    }

    launch {
        mutableSharedFlow.collect {
            delay(1000)
            println(it)
        }
    }

    delay(2000)

    launch {
        println("Start listening")
        mutableSharedFlow.collect {
            delay(1000)
            println("second $it")
        }
    }
}

//A emitted
//B emitted
//C emitted
//D emitted
//E emitted
//Start listening
private suspend fun main3(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<Char>(replay = 0)

    launch {
        for (c in 'A'..'E') {
            delay(300)
            println("$c emitted")
            mutableSharedFlow.emit(c)
        }
    }

    delay(2000)

    launch {
        println("Start listening")
        mutableSharedFlow.collect {
            delay(1000)
            println("second $it")
        }
    }
}