package utils.helpers.y2015

import utils.common.binaryNot

data class Gate(val expression: String) {

    private var cached: Int? = null

    fun isComputed(): Boolean = cached != null

    fun retrieve(): Int? {
        return if (cached == null) null
        else if (cached!! < 0) 0
        else if (cached!! > 65535) 65535
        else cached!!
    }

    fun compute(computedGates: MutableMap<String, Gate>) {
        val components = expression.split(" ").map { it.trim() }
        when (components.size) {
            1 -> {
                // Number or gate copy
                cached = if (components[0].matches("[0-9]*".toRegex()))
                    components[0].toInt()
                else if (computedGates[components[0]]?.isComputed() == true)
                    computedGates[components[0]]!!.retrieve()
                else return
            }
            2 -> {
                // not + gate or number
                if (components[0] == "NOT")
                    cached = if (components[1].matches("[0-9]*".toRegex())) components[1].toInt().binaryNot()
                    else if (computedGates[components[1]]?.isComputed() == true) computedGates[components[1]]!!.retrieve()!!
                        .binaryNot()
                    else return

            }
            3 -> {
                // AND, OR , RSHIFT, LSHIFT

                val left: Int = if (components[0].matches("[0-9]*".toRegex())) components[0].toInt()
                else if (computedGates[components[0]]?.isComputed() == true) computedGates[components[0]]!!.retrieve()!!
                else return


                val right = if (components[2].matches("[0-9]*".toRegex())) components[2].toInt()
                else if (computedGates[components[2]]?.isComputed() == true) computedGates[components[2]]!!.retrieve()!!
                else return

                cached = when (components[1]) {
                    "AND" -> left.and(right)
                    "OR" -> left.or(right)
                    "RSHIFT" -> left.shr(right)
                    "LSHIFT" -> left.shl(right)
                    else -> return
                }
            }
        }
        return
    }
}
