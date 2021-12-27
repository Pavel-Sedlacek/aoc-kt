package utils.helpers.y2021

fun List<Parameters>.findModelNumber(largest: Boolean): Long {
    val stack = ArrayDeque<StackItem>()
    val digits = Array(14) { 0 }
    this.forEachIndexed { digitIndex, parameters ->
        if (parameters.xAddend >= 10) {
            stack.add(StackItem(digitIndex, parameters.yAddend))
        } else {
            val popped = stack.removeLast()
            val addend = popped.addend + parameters.xAddend
            val digit = (if (largest) 9 downTo 1 else 1..9).first { it + addend in 1..9 }
            digits[popped.digitIndex] = digit
            digits[digitIndex] = digit + addend
        }
    }
    return digits.fold(0L) { acc, d -> acc * 10 + d }
}

private class StackItem(val digitIndex: Int, val addend: Int)

class Parameters(val xAddend: Int, val yAddend: Int)