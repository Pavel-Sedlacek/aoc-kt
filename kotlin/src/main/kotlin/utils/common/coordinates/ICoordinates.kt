package utils.common.coordinates

interface ICoordinates {
    fun directNeighbors(): List<ICoordinates>

    fun squareNeighbors(size: Int): List<ICoordinates>
}