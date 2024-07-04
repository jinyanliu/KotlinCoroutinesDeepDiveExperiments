package org.example.test

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.coroutines.CoroutineContext
import kotlin.test.Test
import kotlin.time.measureTime


class TestTest {

    //48.318458ms
    @Test
    fun test1() = println(measureTime {
        runTest {
            assertEquals(0, currentTime)
            delay(1000)
            assertEquals(1000, currentTime)
        }
    })

    @Test
    fun test2() = println(measureTime {
        runTest {
            assertEquals(0, currentTime)
            coroutineScope {
                launch { delay(1000) }
                launch { delay(1500) }
                launch { delay(2000) }
            }
            assertEquals(2000, currentTime)
        }
    })

    @Test
    fun `should increment counter`() = runTest {
        var i = 0
        backgroundScope.launch {
            while (true) {
                delay(1000)
                i++
            }
        }
        delay(1001)
        assertEquals(1, i)
        delay(1000)
        assertEquals(2, i)
        //coroutineContext.job.cancelChildren()
    }


    @Test
    fun `should map async and keep elements order`() = runTest {
        val transforms = listOf(
            suspend { delay(3000); "A" },
            suspend { delay(2000); "B" },
            suspend { delay(4000); "C" },
            suspend { delay(1000); "D" }
        )

        val res = listOf<Int>(0, 1, 2, 3).mapAsync {
            //println("transforms[it] = ${transforms[it]}")
            transforms[it]()
        }
        assertEquals(listOf("A", "B", "C", "D"), res)
        assertEquals(4000, currentTime)

    }

    suspend fun <T, R> Iterable<T>.mapAsync(
        transformation: suspend (T) -> R
    ): List<R> = coroutineScope {
        this@mapAsync.map {
            async {
                //println("transformation(it) = ${transformation(it)}")
                transformation(it)
            }
        }.awaitAll()
    }

    @Test
    fun test3() = println(
        measureTime {
            runBlockingTest {
                println("C")
                delay(1000)
                println("D")
            }
        })

    @Test
    fun test4() = runBlockingTest {
        var i = 0
        launch {
            i = 2
            delay(1000)
            println("Haha")
        }
        println("i = $i")
    }

    @Test
    fun test5() = runTest {
        println("1")
        println("currentTime=$currentTime")
        launch {
            println("2,0")
            delay(1000)
            println("2")
            println("currentTime=$currentTime")
        }
        println("3")
        println("currentTime=$currentTime")
    }

    @Test
    fun `should support context propagation`() = runTest {
            listOf("A").mapAsync {
                println(currentCoroutineContext())
                println(coroutineContext)
                it
            }
    }
}

