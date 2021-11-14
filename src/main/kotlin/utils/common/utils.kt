package utils

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