package org.example.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.Assertions
import kotlin.test.Test
import kotlin.test.assertEquals

class Experiment1 {

    @Test
    fun testRunBlocking() = runBlocking {
        //Test worker @coroutine#1
        println(Thread.currentThread().name)
        var i = 0
        launch {
            //Test worker @coroutine#2
            println(Thread.currentThread().name)
            i = 2
            delay(1000)
            i = 3
        }
        assertEquals(0, i)

        delay(1000)

        assertEquals(2, i)

        delay(1000)

        assertEquals(3, i)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testRunBlockingTest() = runBlockingTest {
        //Test worker @coroutine#1
        println(Thread.currentThread().name)
        var i = 0
        launch {
            //Test worker @coroutine#2
            println(Thread.currentThread().name)
            i = 2
            delay(1000)
            i = 3
        }
        Assertions.assertEquals(2, i)

        delay(1000)

        assertEquals(3, i)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testRunTestUTD() = runTest(UnconfinedTestDispatcher()) {
        //Test worker @coroutine#1
        println(Thread.currentThread().name)
        var i = 0
        launch {
            //Test worker @coroutine#2
            println(Thread.currentThread().name)
            i = 2
            delay(1000)
            i = 3
        }
        Assertions.assertEquals(2, i)

        delay(1000)

        Assertions.assertEquals(3, i)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testRunTestUTD2() = runTest(UnconfinedTestDispatcher()) {
        //Test worker @coroutine#1
        println(Thread.currentThread().name)
        var i = 0
        launch {
            //Test worker @coroutine#2
            println(Thread.currentThread().name)
            i = 2
            delay(1000)
            i = 3
        }
        Assertions.assertEquals(2, i)

        advanceTimeBy(1000)

        runCurrent()

        Assertions.assertEquals(3, i)
    }

    @Test
    fun testRunTest() = runTest {
        //Test worker @kotlinx.coroutines.test runner#2
        println(Thread.currentThread().name)
        var i = 0
        launch {
            //Test worker @coroutine#3
            println(Thread.currentThread().name)
            i = 2
            delay(1000)
            i = 3
        }
        Assertions.assertEquals(0, i)

        delay(1000)

        assertEquals(2, i)

        delay(1000)

        assertEquals(3, i)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testRunTest2() = runTest {
        //Test worker @kotlinx.coroutines.test runner#2
        println(Thread.currentThread().name)
        var i = 0
        launch {
            //Test worker @coroutine#3
            println(Thread.currentThread().name)
            i = 2
            delay(1000)
            i = 3
        }
        Assertions.assertEquals(0, i)

        runCurrent()

        Assertions.assertEquals(2, i)

        advanceTimeBy(1000)

        runCurrent()

        Assertions.assertEquals(3, i)
    }
}

