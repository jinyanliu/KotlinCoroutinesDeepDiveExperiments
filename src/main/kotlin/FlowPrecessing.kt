package org.example

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


suspend fun main() {
    flowOf("A", "B", "C")
        .map {
            delay(1000)
            it.lowercase()
        }
        .collect { println(it) }

    flow {
        flow {
            emit("A")
            emit("B")
            emit("C")
        }.collect {
            delay(1000)
            emit(it.lowercase())
        }
    }
        .collect { println(it) }



    flowOf(1, 2, 3)
        .onEach {
            coroutineScope {
                async {
                    println("Before delay $it")
                    delay(1000)
                    println("After delay $it")
                }
            }
        }
        .collect { println(it) }

}

private fun <T, R> Flow<T>.map(
    transformation: suspend (T) -> R
): Flow<R> = flow {
    collect {
        emit(transformation(it))
    }
}

fun <T> Flow<T>.onEach(
    action: suspend (T) -> Unit
): Flow<T> = flow {
    collect {
        action(it)
        emit(it)
    }
}