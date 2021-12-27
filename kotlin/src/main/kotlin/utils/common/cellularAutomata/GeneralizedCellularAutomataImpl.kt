package utils.common.cellularAutomata

import utils.collections.Coordinates
import utils.collections.applyCoordinates
import utils.collections.copy
import utils.collections.surroundingCoordinates

class GeneralizedCellularAutomataImpl<T>(
    map: List<List<T>>,
    override val rules: List<CellularAutomataRule<T>>,
    override val emptyFieldValue: T,
    override val wrap: Boolean = false
) : GeneralizedCellularAutomata<T> {

    override var map = map.applyCoordinates()
    override var iteration: Int = 0
    override val initialState = this.map.copy(false)
    override var previousIterations: MutableList<Map<Coordinates, T>> = mutableListOf()

    private val width = map.first().size
    private val height = map.size

    override fun next(): GeneralizedCellularAutomata<T> {
        val new = mutableMapOf<Coordinates, T>()
        for ((coordinate, value) in map) {
            var newValue = emptyFieldValue
            val surrounding = map.surroundingCoordinates(
                coordinate,
                default = emptyFieldValue,
                wrap = wrap,
                width = width,
                height = height
            )
            for (i in rules.filter { it.target == value }) {
                newValue = i(value, surrounding, iteration)
            }
            new[coordinate] = newValue
        }
        previousIterations.add(map.copy())
        map = new.copy(false)
        iteration++
        return this
    }

    override fun nextWhile(
        predicate: (iteration: Int, self: GeneralizedCellularAutomata<T>) -> Boolean,
        afterEach: (iteration: Int, self: GeneralizedCellularAutomata<T>) -> Unit
    ): GeneralizedCellularAutomata<T> {
        while (predicate(iteration, this)) {
            next()
            afterEach(iteration, this)
        }
        return this
    }

    override fun nextUntil(
        predicate: (iteration: Int, self: GeneralizedCellularAutomata<T>) -> Boolean,
        afterEach: (iteration: Int, self: GeneralizedCellularAutomata<T>) -> Unit
    ): GeneralizedCellularAutomata<T> {
        while (!predicate(iteration, this)) {
            next()
            afterEach(iteration, this)
        }
        return this
    }

    fun nextUntil(
        predicate: (iteration: Int, self: GeneralizedCellularAutomata<T>) -> Boolean,
    ): GeneralizedCellularAutomata<T> {
        return nextUntil(predicate) { _, _ -> }
    }

    override fun match(predicate: (current: Map<Coordinates, T>, previousIterations: List<Map<Coordinates, T>>) -> Boolean): Boolean =
        predicate(this.map, previousIterations)

    override fun matchesPrevious(comparator: (Map<Coordinates, T>, Map<Coordinates, T>?) -> Boolean): Boolean =
        comparator(this.map, previousIterations.lastOrNull())
}