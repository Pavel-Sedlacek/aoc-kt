package utils.helpers.y2021

fun List<String>.findCommonAtPosition(leastCommon: Boolean, position: Int): Char {
    return count { it[position] == '1' }
        .let { if (it >= size - it) '1' else '0' }
        .let { if (leastCommon) (1 - it.digitToInt()).digitToChar() else it }
}