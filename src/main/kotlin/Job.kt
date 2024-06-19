package org.example

import kotlinx.coroutines.*

fun main12() = runBlocking {
    val job = Job()

    launch(job) {
        val childJob1 = coroutineContext[Job]
        repeat(5){num ->
            println("childJob1=$childJob1")
            delay(200)
            println("Rep$num")
        }
    }

    launch {
        val childJob2 = coroutineContext[Job]
        delay(500)
        val result = job.completeExceptionally(Error("Some error"))
        println("result=$result")
        println("job1=$job")
        println("childJob2=$childJob2")
    }

    println("job2=$job")

    job.join()

    println("job3=$job")

    launch(job) {
        println("will not be printed")
    }

    println("Done")

}

suspend fun main(): Unit = coroutineScope {
    val parentJob = Job()
    val job = Job(parentJob)
    launch(job) {
        delay(1000)
        println("Text 1")
    }
    launch(job) {
        delay(2000)
        println("Text 2")
    }
    delay(1100)
    parentJob.cancel()
    job.children.forEach { it.join() }
}