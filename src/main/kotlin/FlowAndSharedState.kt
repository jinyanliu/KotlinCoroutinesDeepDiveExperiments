package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random


suspend fun main(): Unit = coroutineScope {
    val f1 = List(1000) { "$it" }.asFlow()
    val f2 = List(1000) { "$it" }.asFlow().counter()

    launch { println("f1 1 ${f1.counter().last()}") }
    launch { println("f1 2 ${f1.counter().last()}") }
    launch { println("f2 1 ${f2.last()}") }
    launch { println("f2 2 ${f2.last()}") }
}

private fun Flow<*>.counter(): Flow<Int> {
    var counter = 0
    return this.map {
        counter++
        List(100) { Random.nextLong() }.shuffled().sorted()
        counter
    }
}