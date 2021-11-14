package utils.readers

import utils.collections.asCharLines

fun String.asBytes(): ByteArray = this.trim().toByteArray()

fun String.asFloats(): List<Float> = this.asLines().map { i -> i.toFloat() }

fun String.asInts(): List<Int> = this.asLines().map { i -> i.toInt() }

fun String.asLines(): List<String> = this.trim().lines()

fun String.asLongs(): List<Long> = this.asLines().map { it.toLong() }

fun String.asIntsDividedBy(separator: Regex): List<Int> = this.trim().split(separator).map { it.toInt() }

fun String.asLinesSplitBy(separator: Regex): List<String> = this.trim().split(separator)
fun String.asLinesSplitBy(separator: String): List<String> = this.trim().split(separator)

fun String.asCharLines(): List<List<Char>> = this.asLines().asCharLines()

fun String.asInt(): Int = this.trim().toInt()