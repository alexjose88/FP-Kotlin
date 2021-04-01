package demo.hof

fun main() {

}

fun <A, B, C> partial(a: A, f: (A, B) -> C): (B) -> C {
    return { b -> f(a, b) }
}

fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C {
    return { a -> { b -> f(a, b) } }
}

fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C {
    return {a, b -> f(a)(b)}
}

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return {a -> f(g(a))}
}