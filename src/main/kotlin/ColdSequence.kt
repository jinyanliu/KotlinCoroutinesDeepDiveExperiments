package org.example

fun m(i: Int): Int {
    println("m$i = ${i * i}")
    return i * i
}

fun f(i: Int): Boolean {
    println("f$i = ${i >= 10}")
    return i >= 10
}

fun main() {
    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .map { m(it) }
        .find { f(it) }
        .let { println("main listOf $it") }

    println()

    sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .map { m(it) }
        .find { f(it) }
        .let { println("main sequenceOf $it") }
}