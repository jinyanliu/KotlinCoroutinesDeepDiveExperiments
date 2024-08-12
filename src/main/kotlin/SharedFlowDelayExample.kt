package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

//2
suspend fun main(): Unit = coroutineScope {
    main2()
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
//first A
//C emitted
//Start listening
//first B
//D emitted
//second C
//first C
//E emitted
//second D
//first D
//second E
//first E
private suspend fun main2(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<Char>()

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
            println("first $it")
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