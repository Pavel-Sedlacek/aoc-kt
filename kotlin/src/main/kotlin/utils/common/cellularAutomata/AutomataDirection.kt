package utils.common.cellularAutomata

enum class AutomataDirection(val x: Int, val y: Int) {
    NORTH(0, -1),
    WEST(-1, 0),
    SOUTH(0, 1),
    EAST(1, 0),

    CENTER(0, 0),

    NORTH_EAST(1, -1),
    NORTH_WEST(-1, -1),
    SOUTH_EAST(1, 1),
    SOUTH_WEST(-1, 1);

    companion object {
        fun from(x: Int, y: Int): AutomataDirection =
            values().find { it.x == x && it.y == y } ?: NORTH
    }
}