package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select

suspend fun requestData1(): String {
    delay(100_000)
    println("Waited 100000")
    return "Data1"
}

suspend fun requestData2(): String {
    delay(1000)
    println("Waited 1000")
    return "Data2"
}

val scope = CoroutineScope(SupervisorJob())

suspend fun askMultipleForData(): String {
    val defData1 = scope.async { requestData1() }
    val defData2 = scope.async { requestData2() }
    return select<String> {
        defData1.onAwait { it }
        defData2.onAwait { it }
    }.also { scope.cancel() }
}

suspend fun main657(): Unit = coroutineScope {
    val defData1 = async { requestData1() }
    val defData2 = async { requestData2() }
    println(select<String> {
        defData1.onAwait { it }
        defData2.onAwait { it }
    })
}

suspend fun main(): Unit = coroutineScope {
    println(askMultipleForData2())
}

suspend fun askMultipleForData2(): String = coroutineScope {
    select<String> {
        async { requestData1() }.onAwait { it }
        async { requestData2() }.onAwait { it }
    }.also { coroutineContext.cancelChildren() }
}