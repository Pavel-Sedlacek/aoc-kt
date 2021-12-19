package utils.helpers.y2020

fun calcWaterRoughness(image: Tile): Int {
    val monsters = findAllSeaMonsters(image)
    return if (monsters != 0) {
        image.grid.sumOf { r -> r.count { it } } - monsters * 15
    } else {
        -1
    }
}

private fun findAllSeaMonsters(image: Tile): Int {
    repeat(4) {
        countSeaMonsters(image).let { if (it != 0) return it }

        image.flipVertical()
        countSeaMonsters(image).let { if (it != 0) return it }

        image.flipHorizontal()
        countSeaMonsters(image).let { if (it != 0) return it }

        image.flipVertical()
        countSeaMonsters(image).let { if (it != 0) return it }

        image.flipHorizontal()

        image.rotateRight()
    }

    return 0
}

private fun countSeaMonsters(image: Tile): Int {
    var count = 0
    for (y in 1 until image.grid.size - 1) {
        for (x in 0 until image.grid.size - 19) {
            if (isSeaMonster(image, x, y)) count++
        }
    }

    return count
}

private fun isSeaMonster(image: Tile, x: Int, y: Int): Boolean {
    return if (image[x, y]) {
        (image[x + 18, y - 1] && image[x + 5, y] && image[x + 6, y] && image[x + 11, y] && image[x + 12, y] && image[x + 17, y] && image[x + 18, y] && image[x + 19, y] && image[x + 1, y + 1] && image[x + 4, y + 1] && image[x + 7, y + 1] && image[x + 10, y + 1] && image[x + 13, y + 1] && image[x + 16, y + 1])
    } else false
}

class Tile(val id: Long, var grid: List<List<Boolean>>) {

    // tile connections
    var top: Long? = null
    var right: Long? = null
    var bottom: Long? = null
    var left: Long? = null

    operator fun get(x: Int, y: Int): Boolean = grid[y][x]

    fun removeBorder() {
        grid = grid.drop(1).dropLast(1).map { it.drop(1).dropLast(1) }
    }

    fun getAllBorders(): List<List<Boolean>> {
        val borders = mutableListOf<List<Boolean>>()

        val top = grid.first()
        borders.add(top)
        // borders.add(top.reversed())

        val right = grid.map { it.last() }
        borders.add(right)
        // borders.add(right.reversed())

        val bottom = grid.last().reversed()
        borders.add(bottom)
        // borders.add(bottom.reversed())

        val left = grid.map { it.first() }.reversed()
        borders.add(left)
        // borders.add(left.reversed())

        return borders
    }

    fun rotateRight(times: Int = 1) {   // rotate in place
        repeat(times) {
            grid =
                grid.flatMap { it.withIndex() }.groupBy({ (i, _) -> i }, { (_, v) -> v })
                    .map { (_, v) -> v.reversed() }
        }
    }

    fun flipHorizontal() {
        grid = grid.map { it.reversed() }
    }

    fun flipVertical() {
        grid = grid.reversed()
    }

    fun setConnection(i: Int, tileID: Long) {
        when (i) {
            0 -> top = tileID
            1 -> right = tileID
            2 -> bottom = tileID
            3 -> left = tileID
        }
    }

    fun getConnection(i: Int): Long? {
        return when (i) {
            0 -> top
            1 -> right
            2 -> bottom
            3 -> left
            else -> error("")
        }
    }

    fun print() {
        grid.forEach { it.map { b -> if (b) '#' else '.' }.joinToString("").also { s -> println(s) } }
    }
}

fun Int.opposite(): Int {
    return when (this) {
        0 -> 2
        1 -> 3
        2 -> 0
        3 -> 1
        else -> 0
    }
}