package utils.common.cellularAutomata

import utils.collections.Coordinates

data class CellularAutomataRule<T>(
    val target: T,
    val predicate: (selected: T, surrounding: Map<AutomataDirection, Pair<Coordinates, T>>, iteration: Int) -> T
) {
    operator fun invoke(selected: T, surrounding: Map<AutomataDirection, Pair<Coordinates, T>>, iteration: Int): T =
        predicate(selected, surrounding, iteration)

}