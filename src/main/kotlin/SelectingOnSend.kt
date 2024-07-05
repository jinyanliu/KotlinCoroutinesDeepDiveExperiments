package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.selects.select
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking {

    val c1 = Channel<Char>(capacity = 2)
    val c2 = Channel<Char>(capacity = 2)

    var lastTime = Date().time
    launch {
        for (c in 'A'..'H') {
            delay(400)
            select<Unit> {
                c1.onSend(c) {
                    println(Date().time - lastTime)
                    lastTime = Date().time
                    println("Sent $c to 1")
                }
                c2.onSend(c) {
                    println(Date().time - lastTime)
                    lastTime = Date().time
                    println("Sent $c to 2")
                }
            }
        }
    }

    launch {
        while (true) {
            delay(1000)
            val c = select<String> {
                c1.onReceive {
                    "$it from 1"
                }
                c2.onReceive {
                    "$it from 2"
                }
            }
            println(Date().time - lastTime)
            lastTime = Date().time
            println("Received $c")

            if (c1.isEmpty && c2.isEmpty) coroutineContext.cancel()
        }
    }
}

