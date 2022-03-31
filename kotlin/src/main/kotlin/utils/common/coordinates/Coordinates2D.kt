package utils.common.coordinates

data class Coordinates2D(var x: Int, var y: Int) : ICoordinates {
    override fun directNeighbors(): List<Coordinates2D> {
        return listOf(
            Coordinates2D(x + 1, y),
            Coordinates2D(x - 1, y),
            Coordinates2D(x, y + 1),
            Coordinates2D(x, y - 1)
        )
    }

    override fun squareNeighbors(size: Int): List<Coordinates2D> {
        val list = mutableListOf<Coordinates2D>()
        for (xD in -size..size) {
            for (yD in -size..size) {
                if (xD == 0 && yD == 0) continue
                list.add(Coordinates2D(x + xD, y + yD))
            }
        }
        return list
    }
}