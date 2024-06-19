package org.example

import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.measureTime

suspend fun main(): Unit = println(measureTime {
    coroutineScope {
        val job = Job()
        launch(job) {
            delay(1000)
            println("Text 1")
        }
        launch(job) {
            delay(2000)
            println("Text 2")
        }
        job.complete()
        job.join()
    }
})

suspend fun main56758765(): Unit {

        coroutineScope {
            val job = Job()
            launch(job) {
                delay(1000)
                println("Text 1")
            }
            launch(job) {
                delay(2000)
                println("Text 2")
            }
            job.complete()
            val time = measureTime { job.join() }
            println(time)
        }
}