package utils.common

fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }