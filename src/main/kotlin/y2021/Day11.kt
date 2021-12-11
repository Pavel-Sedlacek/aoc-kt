package y2021

import utils.Day
import utils.collections.Coordinates
import utils.collections.surroundingPoints
import utils.readers.asLines

@ExperimentalStdlibApi
class Day11 : Day<Int> {

    private val octopusSolver = OctopusSolver(file.asLines().flatMapIndexed { index: Int, s: String ->
        s.mapIndexed { i, c ->
            (Coordinates(index, i) to Octopus(
                Coordinates(index, i), c.digitToInt()))
        }
    }.toMap())

    override fun runAll() = super.run({ partOne() }, { partTwo() })

    private fun partOne(): Int = octopusSolver.accessPartOne()

    private fun partTwo(): Int = octopusSolver.accessPartTwo()
}


class Octopus(val coordinate: Coordinates, private var energyLevel: Int) {
    var lastFlashedStep = -1
    var flashCounter = 0

    fun readyToFlash(step: Int) = (step > lastFlashedStep) and (energyLevel > 9)
    fun increaseEnergy(step: Int) = if (step > lastFlashedStep) energyLevel++ else 0
    fun flash(step: Int) = if (readyToFlash(step)) {
        energyLevel = 0
        lastFlashedStep = step
        flashCounter++
        true
    } else false
}

class OctopusSolver(val input: Map<Coordinates, Octopus>) {

    private var cachedPartOne = -1
    private var cachedPartTwo = -1

    fun accessPartOne(): Int = cachedPartOne.also { if (cachedPartOne == -1) compute() }
    fun accessPartTwo(): Int = cachedPartTwo.also { if (cachedPartTwo == -1) compute() }

    private fun compute() {
        repeat(Int.MAX_VALUE) { t ->
            input.onEach { (_, octopus) -> octopus.increaseEnergy(t) }
            while (true) {
                val x = input.filter { (_, value) -> value.readyToFlash(t) }
                if (x.isEmpty()) break
                x.onEach { (_, value) ->
                    if (value.flash(t)) {
                        value.coordinate.surroundingPoints().onEach { point ->
                            input.getOrDefault(point, null)?.increaseEnergy(t)
                        }
                    }
                }
            }
            if (t == 99) {
                cachedPartOne = input.values.sumOf { it.flashCounter }
            }
            if (cachedPartTwo == -1 && input.all { (_, octopus) -> octopus.lastFlashedStep == t }) {
                cachedPartTwo = t + 1
            }
            if (cachedPartTwo != -1 && cachedPartOne != -1) return
        }
    }
}