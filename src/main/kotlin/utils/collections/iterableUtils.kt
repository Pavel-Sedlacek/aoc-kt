package utils.collections

fun Iterable<Int>.product(): Int {
    var x = 1
    for (i in this) x *= i
    return x
}