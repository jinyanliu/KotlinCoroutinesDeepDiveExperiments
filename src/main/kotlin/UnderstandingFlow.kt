package org.example

suspend fun main() {
    val f: suspend ((String) -> Unit) -> Unit = { emit ->
        emit("A")
        emit("B")
        emit("C")
    }
    f({ print("${it + 1L}") })
    f({ print(it) })
}