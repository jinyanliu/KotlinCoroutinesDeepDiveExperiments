package org.example

fun main() {
    val l = buildList {
        repeat(3) {
            add("User$it")
            println("L: Added User$it")
        }
    }

    println(l)

    val l2 = l.map {
        println("L: Processing $it")
        "Processed $it"
    }

    println(l2)

    val s: Sequence<String> = sequence {
        repeat(3) {
            println("S: Added User$it")
            yield("User$it")
        }
    }

    println(s)

    val s2 = s.map {
        println("S: Processing $it")
        "Processed $it"
    }

    println(s2)

    println(s2.take(5).toList())
}