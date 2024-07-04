package org.example

import kotlinx.coroutines.*

fun main60():Unit= runBlocking{
    launch(Job()) {
        delay(1000)
        println("Will not be printed")
    }
}

fun main():Unit = runBlocking {
    val name = CoroutineName("Some name")
    val job = Job()
    val parentJob = coroutineContext.job
    println(job == parentJob)

    launch(name + parentJob){
        currentCoroutineContext()
        delay(1000)
        val childName = coroutineContext[CoroutineName]
        println(childName == name)
        val childJob = coroutineContext[Job]
        println(childJob == parentJob)
        println(childJob == parentJob.children.first())
    }
}