package utils.helpers.y2015

enum class State {
    RESTING,
    SPEEDING
}

data class Reindeer(
    val speed: Int,
    val speedDuration: Int,
    val restDuration: Int
) {

    var distanceTraveled: Int = 0
    private var restRemaining: Int = 0
    private var speedRemaining: Int = speedDuration
    private var state: State = State.SPEEDING
    var score: Int = 0

    fun move() {
        if (state == State.SPEEDING) go()
        else stop()
    }

    private fun go() {
        if (speedRemaining <= 0) {
            restRemaining = restDuration
            state = State.RESTING
            stop()
            return
        }
        distanceTraveled += speed
        speedRemaining--
    }

    private fun stop() {
        if (restRemaining <= 0) {
            speedRemaining = speedDuration
            state = State.SPEEDING
            go()
            return
        }
        restRemaining--
    }
}