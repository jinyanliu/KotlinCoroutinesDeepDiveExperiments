package org.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.measureTime

fun <T> suspendLazy2(
    initializer: suspend () -> T
): suspend () -> T {
    println("suspendLazy2 is called")
    val mutex = Mutex()
    return {
        mutex.withLock {
            val holder = initializer()
            holder
        }
    }
}

suspend fun main() {
    println(measureTime { getConnection() })
    println(measureTime { getConnection() })
    println(measureTime { getConnection() })
}

val getConnection: suspend () -> String = suspendLazy { makeConnection() }

suspend fun makeConnection(): String {
    println("Creating connection")
    delay(1000)
    println("Connection")
    return "Connection"
}

fun <T> suspendLazy(
    initializer: suspend () -> T
): suspend () -> T {
    println("suspendLazy is called")
    var initializer: (suspend () -> T)? = initializer
    println("initializer=$initializer")
    val mutex = Mutex()
    var holder: Any? = Any()

    return {
        println("initializer2=$initializer")
        if (initializer == null) holder as T
        else mutex.withLock {
            initializer?.let {
                holder = it()
                initializer = null
            }
            holder as T
        }
    }
}