package utils.helpers.y2020

fun countDiagonalTrees(x: Int, y: Int, model: MutableList<String>): Long {
    var curX = 0
    var curY = 0
    var curSum: Long = 0

    while (true) {
        curX += x
        curY += y

        if (curY >= model.size) break

        while (curX >= model[curY].length) model[curY] += model[curY]

        if (model[curY][curX] == '#') {
            curSum++
        }
    }
    return curSum
}