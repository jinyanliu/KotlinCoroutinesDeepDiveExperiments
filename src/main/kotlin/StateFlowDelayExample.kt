package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    main4()
}

//XABCDE
private suspend fun main1(): Unit = coroutineScope {
    val state = MutableStateFlow('X')
    launch {
        for (c in 'A'..'E') {
            delay(300)
            state.value = c
        }
    }

    state.collect {
        println(it)
    }
}

//CDE
private suspend fun main2(): Unit = coroutineScope {
    val state = MutableStateFlow('X')
    launch {
        for (c in 'A'..'E') {
            delay(300)
            state.value = c
        }
    }

    delay(1000)
    state.collect {
        println(it)
    }
}

//XCE
private suspend fun main3(): Unit = coroutineScope {
    val state = MutableStateFlow('X')
    launch {
        for (c in 'A'..'E') {
            delay(300)
            state.value = c
        }
    }

    state.collect {
        delay(1000)
        println(it)
    }
}

//X
//Second X
//C
//Second C
//E
//Second E
private suspend fun main4(): Unit = coroutineScope {
    val state = MutableStateFlow('X')
    launch {
        for (c in 'A'..'E') {
            delay(300)
            state.value = c
        }
    }

    state.collect {
        println(it)
        delay(1000)
        println("Second $it")
    }
}

//value=C
//value=E
private suspend fun main5(): Unit = coroutineScope {
    val state = MutableStateFlow('X')
    launch {
        for (c in 'A'..'E') {
            delay(300)
            state.value = c
        }
    }

    repeat(2) {
        delay(1000)
        println("value=${state.value}")
    }
}