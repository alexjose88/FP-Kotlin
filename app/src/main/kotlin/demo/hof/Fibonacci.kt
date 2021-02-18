package demo.hof

fun main() {
    println(
        fibonacci(2)
    )

}

fun fibonacci(n: Int): Int {
    if (n == 0) return 0

    tailrec fun go(
        n: Int,
        it: Int,
        previous: Int,
        current: Int,
    ): Int {
        if (n == it) return current
        return go(
            n = n,
            it = it + 1,
            previous = current,
            current = previous + current
        )
    }

    return go(
        n = n,
        it = 1,
        previous = 0,
        current = 1,
    )
}


