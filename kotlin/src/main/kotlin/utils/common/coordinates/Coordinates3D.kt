package utils.common.coordinates

data class Coordinates3D(val x: Int, val y: Int, val z: Int) : ICoordinates {
    override fun directNeighbors(): List<Coordinates3D> {
        return listOf(
            Coordinates3D(x + 1, y, z),
            Coordinates3D(x - 1, y, z),
            Coordinates3D(x, y + 1, z),
            Coordinates3D(x, y - 1, z),
            Coordinates3D(x, y, z + 1),
            Coordinates3D(x, y, z - 1),
        )
    }

    override fun squareNeighbors(size: Int): List<Coordinates3D> {
        val list = mutableListOf<Coordinates3D>()
        for (xD in -size..size) {
            for (yD in -size..size) {
                for (zD in -size..size) {
                    if (xD == 0 && yD == 0 && zD == 0) continue
                    list.add(Coordinates3D(x + xD, y + yD, z + zD))
                }
            }
        }
        return list
    }
}