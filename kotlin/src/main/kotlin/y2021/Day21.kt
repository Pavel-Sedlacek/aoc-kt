package y2021

import utils.Day
import utils.helpers.y2021.*
import utils.readers.asLines

class Day21 : Day<Number> {

    override fun runAll() = super.run({ partOne() }, { partTwo() })

    private val initialGameState = file.asLines().let { input ->
        GameState(
            PlayerState(input.first().last().digitToInt() - 1, 0),
            PlayerState(input.last().last().digitToInt() - 1, 0)
        )
    }

    private val cache = mutableMapOf<GameState, WonCount>()

    private fun partOne() = solveDeterministicDice(initialGameState)

    private fun partTwo() = solveDiracDice(initialGameState, cache).maxScore()
}