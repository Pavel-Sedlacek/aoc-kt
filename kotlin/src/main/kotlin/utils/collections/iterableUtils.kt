package utils.collections

fun Iterable<Int>.product(): Int = this.fold(0) { acc, i -> acc * i }
