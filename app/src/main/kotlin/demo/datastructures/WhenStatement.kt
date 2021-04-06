package demo.datastructures

fun main() {
    render(Dog)
    println(isPositive(0))
}

sealed class Animal

object Dog : Animal()
object Cat : Animal()


fun render(a: Animal) {
    when (a) {
        is Dog -> println("Is a dog")
        is Cat -> println("Is a cat")

    }
}

fun isPositive(i: Int): String =
    when {
        i == 0 -> "is zero"
        i > 0 -> "is positive"
        else -> "is negative"
    }