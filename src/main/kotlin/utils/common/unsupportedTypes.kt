package utils

fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }
