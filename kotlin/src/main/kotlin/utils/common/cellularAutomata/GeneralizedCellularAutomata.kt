package utils.common.cellularAutomata

import utils.collections.Coordinates
import utils.collections.subMatch

interface GeneralizedCellularAutomata<T> {

    val rules: List<CellularAutomataRule<T>>
    var map: Map<Coordinates, T>
    val initialState: Map<Coordinates, T>
    val emptyFieldValue: T
    var iteration: Int
    var previousIterations: MutableList<Map<Coordinates, T>>
    val wrap: Boolean

    fun next(): GeneralizedCellularAutomata<T>
    fun nextWhile(
        predicate: (iteration: Int, self: GeneralizedCellularAutomata<T>) -> Boolean,
        afterEach: (iteration: Int, self: GeneralizedCellularAutomata<T>) -> Unit = { _, _ -> }
    ): GeneralizedCellularAutomata<T>

    fun nextUntil(
        predicate: (iteration: Int, self: GeneralizedCellularAutomata<T>) -> Boolean,
        afterEach: (iteration: Int, self: GeneralizedCellularAutomata<T>) -> Unit
    ): GeneralizedCellularAutomata<T>

    fun match(predicate: (current: Map<Coordinates, T>, previousIterations: List<Map<Coordinates, T>>) -> Boolean): Boolean

    fun matchesPrevious(comparator: (Map<Coordinates, T>, Map<Coordinates, T>?) -> Boolean = ::subMatch): Boolean
}

