package org.example

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.retry

suspend fun main() {
    flow {
        emit(1)
        emit(2)
        error("E")
        emit(3)
    }.retry(3) {
        println("retry ${it.message}")
        true
    }.collect { println("collect ${it}") }
}