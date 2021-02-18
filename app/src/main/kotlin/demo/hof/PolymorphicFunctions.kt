package demo.hof

fun main() {
    val list = listOf(1, 2, 3, 4)

    println(
        isSorted(list) { a, b -> a <= b }
    )

}


fun <T> isSorted(list: List<T>, order: (T, T) -> Boolean): Boolean {
    tailrec fun loop(current: List<T>): Boolean {
        if (current.tail.isEmpty()) return true

        return if (order(current.head, current.tail.head)) loop(current.tail)
        else false
    }

    return loop(list)
}

val <T> List<T>.head: T
    get() = first()

val <T> List<T>.tail: List<T>
    get() = drop(1)
