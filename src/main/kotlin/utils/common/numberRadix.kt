package utils.common

fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

fun String.toDecimal(): Int = this.toInt(2)

fun Int.toBinary() = this.toString(2)

fun Int.toBinary(binaryString: String): String {
    this.toString(2)
    if (this > 0) {
        return (this / 2).toBinary("${binaryString}${this % 2}")
    }
    return binaryString.reversed()
}

fun String.toBinary(zeroes: List<String>, ones: List<String>): String =
    this.replaceAll(zeroes, "0").replaceAll(ones, "1")


fun Int.binaryNot(pad: Int = 16): Int =
    this.toBinary().padStart(pad, '0').let { it.map { if (it == '0') 1 else 0 }.joinToString("").toDecimal() }