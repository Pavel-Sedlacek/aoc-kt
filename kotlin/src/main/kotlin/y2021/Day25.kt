package y2021

import utils.Day
import utils.common.automata.CellularAutomataRule
import utils.common.coordinates.Coordinates2D
import utils.readers.asCharLines

class Day25 : Day<String> {

    private val input = file.asCharLines()
    private val rules = listOf<CellularAutomataRule<Char, Coordinates2D>>(
//        CellularAutomataRule('.') { selected, surrounding, iteration ->
//            if (iteration % 2 == 0)
//                if (surrounding[AutomataDirection.WEST]!!.second == '>') '>'
//                else selected
//            else if (iteration % 2 == 1)
//                if (surrounding[AutomataDirection.NORTH]!!.second == 'v') 'v'
//                else selected
//            else '.'
//        },
//        CellularAutomataRule('>') { selected, surrounding, iteration -> if (iteration % 2 == 0 && selected == '>' && surrounding[AutomataDirection.EAST]!!.second == '.') '.' else selected },
//        CellularAutomataRule('v') { selected, surrounding, iteration -> if (iteration % 2 == 1 && selected == 'v' && surrounding[AutomataDirection.SOUTH]!!.second == '.') '.' else selected },
    )

    override fun runAll() = super.run({ partOne(input, rules) }) { partTwo() }

    private fun partOne(input: List<List<Char>>, rules: List<CellularAutomataRule<Char, Coordinates2D>>): Nothing =
        TODO()

    private fun partTwo() = "EZ YOU WIN"
}