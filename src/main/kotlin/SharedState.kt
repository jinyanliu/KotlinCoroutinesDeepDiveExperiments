package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

suspend fun main() = coroutineScope {
    val semaphore = Semaphore(2)

    /*repeat(5){
        launch {
            semaphore.withPermit {
                delay(1000)
                println(it)
            }
        }
    }*/

    repeat(16){
        launch {
            LimitedNetworkUserRepository().requestUser(it)
        }
    }
}

class LimitedNetworkUserRepository{
    private val semaphore = Semaphore(2)

    suspend fun requestUser(userId: Int) =
        semaphore.withPermit {
            delay(1000)
            println(userId)
        }
}