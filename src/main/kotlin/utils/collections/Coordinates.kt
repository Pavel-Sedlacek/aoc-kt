package utils.collections

data class Coordinates(var x: Int, var y: Int) {
    fun moveTowards(other: Coordinates): Coordinates {
        val x = when {
            this.x == other.x -> this.x
            this.x > other.x -> this.x - 1
            else -> this.x + 1
        }
        val y = when {
            this.y == other.y -> this.y
            this.y > other.y -> this.y - 1
            else -> this.y + 1
        }
        return Coordinates(x, y)
    }
}