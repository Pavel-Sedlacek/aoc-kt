package utils.helpers.y2021

private val diracDice = (1..3).flatMap { x -> (1..3).flatMap { y -> (1..3).map { z -> x + y + z } } }
    .groupingBy { it }
    .eachCount()
    .mapValues { it.value.toLong() }

private fun rollDeterministic(turn: Int) = (turn * 3) + (turn * 3 + 1) + (turn * 3 + 2) + 3

tailrec fun solveDeterministicDice(gameState: GameState, turn: Int = 0): Int = when {
    gameState.hasAWinner() -> gameState.minScore() * turn * 3
    else -> solveDeterministicDice(gameState.next(rollDeterministic(turn)), turn + 1)
}

fun solveDiracDice(gameState: GameState, cache: MutableMap<GameState, WonCount>): WonCount = when {
    gameState.p1State.score >= 21 -> WonCount(1, 0)
    gameState.p2State.score >= 21 -> WonCount(0, 1)
    else -> cache.getOrPut(gameState) {
        diracDice.map { (forward, freq) -> solveDiracDice(gameState.next(forward), cache) * freq }
            .reduce { acc, count -> acc + count }
    }
}

data class GameState(val p1State: PlayerState, val p2State: PlayerState, val p1Turn: Boolean = true) {
    fun next(rollSum: Int): GameState {
        return GameState(
            if (p1Turn) p1State.next(rollSum) else p1State,
            if (!p1Turn) p2State.next(rollSum) else p2State,
            !p1Turn
        )
    }

    fun hasAWinner(neededScore: Int = 1000) = p1State.score >= neededScore || p2State.score >= neededScore

    fun minScore() = minOf(p1State.score, p2State.score)
}

data class PlayerState(val position: Int, val score: Int) {
    fun next(rollSum: Int): PlayerState {
        val p1NewPosition = (position + rollSum) % 10
        return PlayerState(p1NewPosition, score + p1NewPosition + 1)
    }
}

class WonCount(private val p1: Long, private val p2: Long) {
    operator fun plus(other: WonCount) = WonCount(p1 + other.p1, p2 + other.p2)

    operator fun times(other: Long) = WonCount(p1 * other, p2 * other)

    fun maxScore() = maxOf(p1, p2)
}