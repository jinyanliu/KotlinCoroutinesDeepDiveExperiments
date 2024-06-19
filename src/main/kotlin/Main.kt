package org.example

import kotlin.random.Random

fun main() {
    val iterator = randomNumbers().iterator()
    println(iterator.next())
    println(iterator.next())
    println(iterator.next())
    println(iterator.next())
    println(iterator.next())
    println(iterator.next())
    println()

    val iterator2 = randomNumbers2().iterator()
    println(iterator2.next())
    println(iterator2.next())
    println(iterator2.next())
    println(iterator2.next())
    println(iterator2.next())
    println(iterator2.next())
    println()

    val iterator3 = randomNumbers3().iterator()
    println(iterator3.next())
    println(iterator3.next())
    println(iterator3.next())
    println(iterator3.next())
    println(iterator3.next())
    println(iterator3.next())
    println()
}

fun randomNumbers(): Sequence<Int> = sequence {
    val random = Random(System.currentTimeMillis())
    while (true) {
        yield(random.nextInt(0, 1000))
    }
}

fun randomNumbers2(): Sequence<Int> = sequence {
    while (true) {
        yield(Random(System.currentTimeMillis()).nextInt(0, 1000))
    }
}

fun randomNumbers3(): Sequence<Int> = sequence {
    while (true) {
        yield(Random.nextInt(0, 1000))
    }
}