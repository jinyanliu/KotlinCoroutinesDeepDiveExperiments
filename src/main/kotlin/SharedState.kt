package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

suspend fun main() {
    withRepository()
    //directCall()
}

private suspend fun directCall() = coroutineScope {
    val semaphore = Semaphore(2)
    repeat(5){
        launch {
            semaphore.withPermit {
                delay(1000)
                println(it)
            }
        }
    }
}

private suspend fun withRepository() = coroutineScope {
    val repository = SomeRepository()
    repeat(20) {
        launch {
            repository.request(it)
        }
    }
}

class SomeRepository{
    private val semaphore = Semaphore(2)

    suspend fun request(userId: Int) =
        semaphore.withPermit {
            delay(1000)
            println(userId)
        }
}