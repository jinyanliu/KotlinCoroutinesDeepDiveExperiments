package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import java.util.concurrent.Executors

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(){
    val dispatcher = Executors
        .newSingleThreadExecutor()
        .asCoroutineDispatcher()

    Dispatchers.setMain(dispatcher)

    coroutineScope {
        launch(Dispatchers.Main) {
            println(Thread.currentThread().name)
        }
    }
}

class SomeTest{
    private val dispatcher = Executors
        .newSingleThreadExecutor()
        .asCoroutineDispatcher()
}