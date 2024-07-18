package org.example

/*fun interface FlowCollector<T> {
    suspend fun emit(value: T)
}

interface Flow<T> {
    suspend fun collect(collector: FlowCollector<T>)
}

fun <T> flow(
    builder: suspend FlowCollector<T>.() -> Unit
) = object : Flow<T> {
    override suspend fun collect(collector: FlowCollector<T>) {
        collector.builder()
    }
}

suspend fun main() {
    val f: Flow<String> = flow {
        println("PrintingA")
        emit("A")
        println("PrintingB")
        emit("B")
        println("PrintingC")
        emit("C")
    }
    f.collect { println(it) }
    f.collect{}
}*/