package org.example

import kotlinx.coroutines.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.resume

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun main94857(): Unit =
    withContext(Dispatchers.Unconfined) {
        var continuation: Continuation<Unit>? = null

        launch(Dispatchers.Unconfined) {
            delay(1000)
            continuation?.resume(Unit)
        }

        launch(Dispatchers.Unconfined) {
            println(Thread.currentThread().name)
            suspendCancellableCoroutine<Unit> {
                continuation = it
            }

            println(Thread.currentThread().name)

            delay(1000)

            println(Thread.currentThread().name)
        }
    }

suspend fun maind344342(): Unit =
    withContext(Dispatchers.Unconfined) {


        println(Thread.currentThread().name)

        delay(1000)


        println(Thread.currentThread().name)

    }

suspend fun main(): Unit =
    withContext(Dispatchers.IO) {

        println(this.coroutineContext[ContinuationInterceptor])
        println(Thread.currentThread().name)

        delay(1000)

        println(this.coroutineContext[ContinuationInterceptor])
        println(Thread.currentThread().name)

    }

suspend fun main4543545(): Unit =
    withContext(Dispatchers.Unconfined) {
        println(this.coroutineContext[Job])
        println(this.coroutineContext[ContinuationInterceptor])

        println(Thread.currentThread().name)

        delay(1000)

        println(Thread.currentThread().name)

    }