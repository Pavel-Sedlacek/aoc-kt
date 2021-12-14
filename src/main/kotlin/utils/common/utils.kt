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

fun String.toDecimal(): Int = this.toInt(2)

fun Int.toBinary() = this.toString(2)

fun Int.toBinary(binaryString: String): String {
    this.toString(2)
    if (this > 0) {
        return (this / 2).toBinary("${binaryString}${this % 2}")
    }
    return binaryString.reversed()
}

fun pow(base: Int, exponent: Int) = base.toDouble().pow(exponent.toDouble()).toInt()

fun Int.not(): Int =
    this.toBinary().padStart(16, '0').let { it.map { if (it == '0') 1 else 0 }.joinToString("").toDecimal() }


fun String.memorySize(): Int =
    this.replace("\\\\", "*").replace("\\\"", "*").replace("\\\\x[0-9a-f]{2}".toRegex(), "*").length

operator fun String.times(other: Int): String = this.repeat(other)

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

fun String.binaryInverse(): String {
    return this.replace("0", "t").replace("1", "0").replace("t", "1")
}


fun List<String>.filterByCommonDigit(position: Int, invert: Boolean): List<String> {
    if (size == 1) return this
    val commonDigit = this.mostCommonCharacterAt(position)
    return filter { (it[position] == commonDigit) xor invert }
}

operator fun Int.contains(values: List<Int>): Boolean = values.contains(this)

fun List<String>.mostCommonLettersAtIndices(keep: Char = '1'): String {
    val s = StringBuilder()
    repeat(this.first().length) {
        s.append(this.mostCommonCharacterAt(it))
    }
    return s.toString()
}

fun List<String>.mostCommonCharacterAt(position: Int): Char {
    return count { it[position] == '1' }.let { if (it >= size - it) '1' else '0' }
}

fun String.mostCommonCharacter(): Char? {
    return this.toList().groupBy { it }.map { it.key to it.value.count() }.maxByOrNull { it.second }?.first
}
fun String.leastCommonCharacter(): Char? {
    return this.toList().groupBy { it }.map { it.key to it.value.count() }.minByOrNull { it.second }?.first
}
fun String.mostCommonCharacterCount(): Int? {
    return this.toList().groupBy { it }.map { it.key to it.value.count() }.maxOfOrNull { it.second }
}
fun String.leastCommonCharacterCount(): Int? {
    return this.toList().groupBy { it }.map { it.key to it.value.count() }.minOfOrNull { it.second }
}

fun Long.incrementalCount(): Long {
    var acc = 0L
    for (i in 0..this) {
        acc += i
    }
    return acc
}

fun <E> List<E>.middle(): E? = this.getOrNull(this.size / 2)