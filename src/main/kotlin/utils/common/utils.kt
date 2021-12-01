package utils.common

import kotlin.math.pow


fun Any.log() {
    println(this)
}

fun Int.isOdd(): Boolean = this % 2 != 0
fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

fun String.toBinary(zeroes: List<String>, ones: List<String>): String =
    this.replaceAll(zeroes, "0").replaceAll(ones, "1")

fun String.replaceAll(toReplace: List<String>, with: String): String =
    this.replace(toReplace.joinToString(")|(", "(", ")").toRegex(), with)

fun String.pureLength(): Int {
    return StringBuilder("\\\"").also {
        for (i in this) it.append("${if (i == '\\' || i == '\"') '\\' else ""}$i")
        it.append("\\\"")
    }.length
}

fun String.toDecimal(): Int {
    var sum = 0
    this.reversed().forEachIndexed { k, v ->
        sum += v.toString().toInt() * pow(2, k)
    }
    return sum
}

fun Int.toBinary(binaryString: String = ""): String {
    while (this > 0) {
        val temp = "${binaryString}${this % 2}"
        return (this / 2).toBinary(temp)
    }
    return binaryString.reversed()
}

fun pow(base: Int, exponent: Int) = base.toDouble().pow(exponent.toDouble()).toInt()

fun Int.not(): Int =
    this.toBinary().padStart(16, '0').let { it.map { if (it == '0') 1 else 0 }.joinToString("").toDecimal() }


fun String.memorySize(): Int {
    var x = this
    x = x.replace("\\\\", "*")
    x = x.replace("\\\"", "*")
    x = x.replace("\\\\x[0-9a-f]{2}".toRegex(), "*")
    return x.length
}

operator fun String.times(other: Int): String {
    return this.repeat(other)
}

fun String.increment(): String = (this.last() + 1).let {
    if (it > 'z') this.substring(0 until this.length - 1).increment() + 'a'
    else this.substring(0 until this.length - 1) + it
}

fun Int.opposite(): Int {
    return when (this) {
        0 -> 2
        1 -> 3
        2 -> 0
        3 -> 1
        else -> 0
    }
}

fun Int.divisors(): List<Int> {
    val v = mutableListOf<Int>()
    for (i in 1..this) {
        if (this % i == 0) v.add(i)
    }
    return v
}

fun Int.limitBy(min: Int = 0, max: Int = 0): Int {
    return if (this < min) min
    else if (this > max) max
    else this
}