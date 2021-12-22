package y2021

import utils.Day
import utils.helpers.y2021.Point3D
import utils.helpers.y2021.Scanner
import utils.helpers.y2021.assembleMap

class Day19 : Day<Int> {

    private val scanners = file
        .split("""--- scanner \d+ ---""".toRegex())
        .filterNot { it.isEmpty() }
        .map { s ->
            Scanner(
                s.trim().lines()
                    .map { it.split(",") }
                    .map { (x, y, z) -> Point3D(x.toInt(), y.toInt(), z.toInt()) }
                    .toSet()
            )
        }

    override fun runAll() = super.run({ partOne(scanners) }) { partTwo(scanners) }

    private fun partOne(scanners: List<Scanner>): Int = assembleMap(scanners).beacons.size

    private fun partTwo(scanners: List<Scanner>): Int = assembleMap(scanners).scannersPositions.let { positions ->
        positions.flatMapIndexed { index, first -> positions.drop(index + 1).map { second -> first to second } }
            .maxOf { (first, second) -> first distanceTo second }
    }

}

