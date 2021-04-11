package demo.datastructures

fun main() {
    println("-------")
    println("tail")
    val list = List.of(1, 2, 3)
    println(
        List.tail(list)
    )

    println("-------")
    println("setHead")
    val list2 = List.of(1, 2, 3)
    println(
        List.setHead(list2, 5)
    )

    println("-------")
    println("drop")
    val list3 = List.of(1, 2, 3)
    println(
        List.drop(list3, 1)
    )

    println("-------")
    println("dropWhile")
    val list4 = List.of(3, 2, 1)
    println(
        List.dropWhile(list4) { i -> i > 2 }
    )

    println("-------")
    println("append")
    val list5 = List.of(1, 2, 3)
    val list6 = List.of(4, 5, 6)
    println(list5)
    println(list6)
    println(
        List.append(list5, list6)
    )

    println("-------")
    println("init")
    val list7 = List.of(1, 2, 3, 4)
    println(list7)
    println(
        List.init(list7)
    )

    println("-------")
    println("sum")
    val list8 = List.of(1, 2, 3)
    println(
        List.sum(list8)
    )
    println(
        List.sumRefactored(list8)
    )

    println("-------")
    println("Prod")
    val list9 = List.of(1.0, 2.0, 3.0)
    println(
        List.product(list9)
    )
    println(
        List.productRefactored(list9)
    )

    println("-------")
    println("Length")
    println(
        List.length(
            List.of(1, 2, 3)
        )
    )

    println("-------")
    println("SumTailRec")
    println(
        List.sumTailRec(
            List.of(1, 2, 3)
        )
    )

    println("-------")
    println("ProdTailRec")
    println(
        List.prodTailRec(
            List.of(2.0, 2.0, 3.0)
        )
    )

    println("-------")
    println("LengthTailRec")
    println(
        List.lengthTailRec(
            List.of(1, 2, 3)
        )
    )

    println("-------")
    println("ReverseTailRec")
    println(
        List.reverseTailRec(
            List.of(1, 2, 3)
        )
    )

    println("-------")
    println("AppendTailRec")
    println(
        List.appendFoldRight(
            List.of(1, 2, 3),
            List.of(4, 5, 6)
        )
    )

    println("-------")
    println("Concat")
    println(
        List.concat(
            List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6)
            )
        )
    )

    println("-------")
    println("plusOne")
    println(
        List.plusOne(
            List.of(1, 2, 3)
        )
    )

    println("-------")
    println("map")
    println(
        List.map(List.of(1, 2, 3)) { a -> a.toString() + "letter" }
    )

    println("-------")
    println("filter")
    println(
        List.filter2(List.of(1, 2, 3)) { a -> a == 2 || a == 3 }
    )

    println("-------")
    println("flatMap")
    println(
        List.flatMap(List.of(1, 2, 3)) { a -> List.of(a, a) }
    )

    println("-------")
    println("sumLists")
    println(
        List.sumLists(
            List.of(1,2,3),
            List.of(2,3,4)
        )
    )

    println("-------")
    println("zipWith")
    println(
        List.zipWith(
            List.of(1,2,3),
            List.of(2,3,4)
        ){a, b -> a + b}
    )
}


sealed class List<out A> {
    companion object {

        /**
         * Creates a new List based on the elements from the input
         */
        fun <A> of(vararg elements: A): List<A> {
            val tail = elements.sliceArray(1 until elements.size)
            return if (elements.isEmpty()) Nil else Cons(elements[0], of(*tail))
        }

        /**
         * Returns what we understand that is an empty list
         */
        fun <A> empty(): List<A> = Nil

        /**
         * Creates a new list with the head from input and as tail the input list
         */
        fun <A> setHead(xs: List<A>, head: A): List<A> =
            when (xs) {
                is Cons -> Cons(head, xs)
                is Nil -> throw IllegalStateException("Cannot set `head` for Nil")
            }

        /**
         * Returns the tail of the input list
         */
        fun <A> tail(xs: List<A>): List<A> =
            when (xs) {
                is Cons -> xs.tail
                is Nil -> throw IllegalStateException("Nil cannot have a `tail`")
            }

        /**
         * Returns a new List without the n first elements
         */
        fun <A> drop(xs: List<A>, n: Int): List<A> =
            if (n <= 0) xs
            else when (xs) {
                is Cons -> drop(xs.tail, n - 1)
                is Nil -> throw IllegalStateException("Nil cannot `drop`")
            }


        /**
         * Returns a new List without the first elements that are evaluated as true by the input function
         */
        fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> =
            when (l) {
                is Cons ->
                    if (f(l.head)) dropWhile(l.tail, f)
                    else l
                is Nil -> l
            }

        /**
         * Creates a new list based on the concatenation of the input lists
         */
        fun <A> append(a1: List<A>, a2: List<A>): List<A> =
            when (a1) {
                is Nil -> a2
                is Cons -> Cons(a1.head, append(a1.tail, a2))
            }

        /**
         * Creates a new list without the input list tail
         */
        fun <A> init(l: List<A>): List<A> =
            when (l) {
                is Cons ->
                    when (l.tail) {
                        is Nil -> Nil
                        is Cons -> Cons(l.head, init(l.tail))
                    }
                is Nil ->
                    throw IllegalStateException("Cannot init Nil list")
            }

        /**
         * Sums all the elements within the list
         */
        fun sum(l: List<Int>): Int =
            when (l) {
                is Nil -> 0
                is Cons -> l.head + sum(l.tail)
            }

        /**
         * Multiplies all the elements within the list
         */
        fun product(l: List<Double>): Double =
            when (l) {
                is Nil -> 1.0
                is Cons -> l.head * product(l.tail)
            }

        /**
         * Refactored version of sum
         */
        fun sumRefactored(l: List<Int>): Int =
            foldRight(l, 0, { a, b -> a + b })

        /**
         * Refactored version of product
         */
        fun productRefactored(l: List<Double>): Double =
            foldRight(l, 1.0, { a, b -> a * b })

        /**
         * Returns the length of the list
         */
        fun <A> length(l: List<A>): Int =
            foldRight(l, 0, { _, acc -> 1 + acc })

        fun <A> appendFoldRight(a1: List<A>, a2: List<A>): List<A> =
            foldRight(a1, a2, { a, b -> Cons(a, b) })

        fun <A> concat(l: List<List<A>>): List<A> =
            foldRight(
                l = l,
                z = List.empty(),
                f = { a, b -> foldRight(a, b, { f, g -> Cons(f, g) }) }
            )

        fun <A> concat2(l: List<List<A>>): List<A> =
            foldRight(
                l = l,
                z = List.empty(),
                f = { a, b -> append(a, b) }
            )

        fun plusOne(xs: List<Int>): List<Int> =
            foldRight(xs, empty(), { a, b -> Cons(a + 1, b) })


        fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> =
            foldRightL(xs, empty(), { a, b -> Cons(f(a), b) })

        fun <A> filter(xs: List<A>, f: (A) -> Boolean): List<A> =
            foldRightL(xs, empty(), { a, b -> if (f(a)) Cons(a, b) else b })

        fun <A> filter2(xs: List<A>, f: (A) -> Boolean): List<A> =
            flatMap(xs) { a -> if (f(a)) of(a) else empty() }


        fun <A, B> flatMap(xa: List<A>, f: (A) -> List<B>): List<B> =
            foldRightL(xa, empty(), {a, acc -> append(f(a), acc)})

        fun sumLists(a1: List<Int>, a2: List<Int>): List<Int> =
            when (a1) {
                is Nil -> Nil
                is Cons -> when(a2) {
                    is Nil -> Nil
                    is Cons -> Cons( a1.head + a2.head, sumLists(a1.tail, a2.tail))
                }
            }

        fun <A>zipWith(a1: List<A>, a2: List<A>, f: (A, A) -> A): List<A> =
            when (a1) {
                is Nil -> Nil
                is Cons -> when(a2) {
                    is Nil -> Nil
                    is Cons -> Cons(f(a1.head, a2.head), zipWith(a1.tail, a2.tail, f))
                }
            }


        private fun <A, B> foldRight(l: List<A>, z: B, f: (A, B) -> (B)): B =
            when (l) {
                is Nil -> z
                is Cons -> f(l.head, foldRight(l.tail, z, f))
            }

        private fun <A, B> foldRightL(l: List<A>, z: B, f: (A, B) -> (B)): B =
            foldLeft(
                xs = l,
                acc = { b: B -> b },
                f = { g, a ->
                    { b ->
                        g(f(a, b))
                    }

                }
            )(z)

        fun sumTailRec(xs: List<Int>): Int =
            foldLeft(xs, 0, { b, a -> b + a })

        fun prodTailRec(xs: List<Double>): Double =
            foldLeft(xs, 1.0, { b, a -> b * a })

        fun <A> lengthTailRec(xs: List<A>): Int =
            foldLeft(xs, 0, { acc, _ -> acc + 1 })

        fun <A> reverseTailRec(xs: List<A>): List<A> =
            foldLeft(xs, List.empty(), { b: List<A>, a: A -> Cons(a, b) })

        private tailrec fun <A, B> foldLeft(xs: List<A>, acc: B, f: (B, A) -> B): B =
            when (xs) {
                is Nil -> acc
                is Cons -> {
                    foldLeft(xs.tail, f(acc, xs.head), f)
                }
            }
    }
}

object Nil : List<Nothing>()

data class Cons<out A>(
    val head: A,
    val tail: List<A>
) : List<A>()