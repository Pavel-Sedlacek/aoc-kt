package utils.common

import kotlin.math.pow

fun Any.log() {
    println(this)
}

fun Int.isOdd(): Boolean = this % 2 != 0
fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

fun String.replaceAll(toReplace: List<String>, with: String): String =
    this.replace(toReplace.joinToString(")|(", "(", ")").toRegex(), with)

fun pow(base: Int, exponent: Int) = base.toDouble().pow(exponent.toDouble()).toInt()

operator fun String.times(other: Int): String = this.repeat(other)

fun Int.divisors(): List<Int> {
    val v = mutableListOf<Int>()
    for (i in 1..this) {
        if (this % i == 0) v.add(i)
    }
    return v
}

val <T1, T2> Pair<T1, T2>.flipped: Pair<T2, T1> get() = second to first