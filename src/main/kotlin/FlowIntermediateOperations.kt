package org.example

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

/*suspend fun main() {
    val flow = flow { emit("Message 1") }
        .onEach { println(it) }
        .onStart { println("Do something before") }
        .onCompletion { println("Do something after") }
        .catch { emit("Error") }

    flow.collect {
        println("Collected $it")
    }

    val mutableFlow: MutableStateFlow<String> = MutableStateFlow("Message 2")
    mutableFlow.emit("Message 3")

    mutableFlow.emit("Message 4")
}*/

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun getOffers(
    sellers: List<String>
): List<Int> = sellers
    .asFlow()
    .flatMapMerge(concurrency = 20) { seller ->
        suspend { transform(seller) }.asFlow()
        //flow { emit(transform(seller)) }
    }
    .toList()

suspend fun transform(s: String): Int {
    delay(1000)
    return s.toInt()
}