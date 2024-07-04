package org.example.test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.Assertions
import kotlin.test.Test
import kotlin.test.assertEquals

class Experiment2 {

    @Test
    fun test6() = runBlocking {
        var i = 0
        launch {
            i = 2
        }
        assertEquals(0, i)
        delay(1000)
        assertEquals(2, i)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test3() = runBlockingTest {
        var i = 0
        launch {
            i = 2
        }
        Assertions.assertEquals(2, i)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test5() = runTest(UnconfinedTestDispatcher()) {
        var i = 0
        launch {
            i = 2
        }
        Assertions.assertEquals(2, i)
    }

    @Test
    fun test4() = runTest {
        var i = 0
        launch {
            i = 2
        }
        assertEquals(0, i)

        delay(1000)

        assertEquals(2, i)
    }
}