package utils.helpers.y2021

fun countIncreased(input: List<Int>, windowSize: Int) =
    input.windowed(windowSize).count { window -> window.last() > window.first() }