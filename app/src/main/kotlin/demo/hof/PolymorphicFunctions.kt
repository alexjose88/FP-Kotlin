package demo.hof

fun main() {
    val list1 = listOf(1, 2, 3, 4)
    val list2 = listOf(4,2,5,6)

    println(
        isSorted(list1) { a, b -> a <= b }
    )

    println(
        isSorted(list2) { a, b -> a <= b }
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
