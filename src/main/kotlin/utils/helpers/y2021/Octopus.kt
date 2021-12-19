package utils.helpers.y2021

class Octopuses(private val energies: Array<Array<Int>>) {

    fun increaseEnergy() {
        energies.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ ->
                energies[y][x]++
            }
        }
    }

    fun flash(): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        energies.forEachIndexed { y, row ->
            row.forEachIndexed { x, energy ->
                if (energy > 9 && (y to x) !in visited) {
                    val toVisit = ArrayDeque(listOf(y to x))
                    visited.add(y to x)
                    while (toVisit.isNotEmpty()) {
                        val (nextY, nextX) = toVisit.removeLast()
                        neighbours(nextY, nextX)
                            .filter { it !in visited }
                            .filter { (y, x) -> ++energies[y][x] > 9 }
                            .forEach { (y, x) ->
                                toVisit.addLast(y to x)
                                visited.add(y to x)
                            }
                    }
                }
            }
        }
        return visited.size
    }

    fun resetFlashed() {
        energies.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ ->
                energies[y][x] = if (energies[y][x] > 9) 0 else energies[y][x]
            }
        }
    }

    fun areNotSynchronized() = energies.any { row -> row.any { energy -> energy > 0 } }

    private fun neighbours(y: Int, x: Int): List<Pair<Int, Int>> {
        return (-1..1).flatMap { dy -> (-1..1).map { dx -> dy to dx } }
            .filterNot { (dy, dx) -> dy == 0 && dx == 0 }
            .map { (dy, dx) -> y + dy to x + dx }
            .filter { (y, x) -> y in energies.indices && x in energies.first().indices }
    }
}

fun sequenceOfFlashes(octopuses: Octopuses) = generateSequence {
    octopuses.increaseEnergy()
    octopuses.flash().also { octopuses.resetFlashed() }
}