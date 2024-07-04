package org.example.test

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

suspend fun main(): Unit = coroutineScope {
    val ctx1 = currentCoroutineContext()
    val ctx2 = coroutineContext

    println("ctx1 = ${ctx1}, ctx2 = ${ctx2}")

    launch(CoroutineName("NewName")) {
        val ctx3 = currentCoroutineContext()
        val ctx4 = coroutineContext

        println("ctx3 = ${ctx3}, ctx4 = ${ctx4}")
    }

    launch { // this: CoroutineScope
        println(coroutineContext)
        val flow = flow<CoroutineContext> {
            emit(currentCoroutineContext())
        }

        flow.collect {
            println(it)
        }
    }
}