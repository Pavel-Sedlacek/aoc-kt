package y2021

import utils.Day
import utils.readers.asLines
import java.math.BigInteger


class Day18 : Day<BigInteger> {

    val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<String>): BigInteger {
        var mp: MathPair? = null
        for (i in input) {
            if (mp == null) {
                mp = MathPair(i)
                continue
            }
            mp = MathPair(mp, MathPair(i))
            mp.setDepths(1)
            while ((mp.maxDepth() > 4) or (mp.maxNum() > 9)) {
                while (mp.explode() != null) {
                    mp.setDepths(1)
                }
                mp.split()
                mp.setDepths(1)
            }
        }
        return mp!!.getMagnitude()
    }

    private fun partTwo(input: List<String>): BigInteger {
        var largestMagnitude = BigInteger.ZERO
        for (i in input) {
            for (j in input) {
                if (i == j) continue
                val mp1 = MathPair(i)
                val mp2 = MathPair(j)

                val mp = MathPair(mp1, mp2)

                mp.setDepths(1)
                while ((mp.maxDepth() > 4) or (mp.maxNum() > 9)) {
                    while (mp.explode() != null) {
                        mp.setDepths(1)
                    }
                    mp.split()
                    mp.setDepths(1)
                }

                mp.getMagnitude().also {
                    if (it > largestMagnitude) largestMagnitude = it
                }
            }
        }

        return largestMagnitude
    }
}


class MathPair() {
    private var left: Any = 0
    private var right: Any = 0
    var depth = 0
    var visited = false

    constructor(s: String) : this() {
        var begin = 0
        var end = s.length
        if (s.startsWith("[")) begin++
        if (s.endsWith("]")) end--

        val terms = s.substring(begin, end).splitTerms()

        val (left, right) = terms.split("|")

        if (left.contains(",")) this.left = MathPair(left)
        else this.left = left.toInt()

        if (right.contains(",")) this.right = MathPair(right)
        else this.right = right.toInt()
    }

    constructor(left: MathPair, right: MathPair) : this() {
        this.left = left
        this.right = right
    }

    fun setDepths(d: Int) {
        depth = d
        (left as? MathPair)?.setDepths(d + 1)
        (right as? MathPair)?.setDepths(d + 1)
        this.visited = false
    }

    fun explode(): MathPair? {
        var mp: MathPair? = null
        if (left is MathPair) mp = (left as MathPair).explode()
        if (mp == null && right is MathPair) mp = (right as MathPair).explode()

        if (depth > 4 && left is Int && right is Int) return this

        if (mp != null) {
            if (this.left == mp) {
                this.left = 0
                this.visited = true
                if (right is MathPair) {
                    if ((right as MathPair).visited) return mp
                    return (right as MathPair).setRight(mp)
                } else {
                    this.right = (this.right as Int) + (mp.right as Int)
                    mp.right = -1
                    return mp
                }
            } else if (this.right == mp) {
                this.right = 0
                this.visited = true

                if (left is MathPair) {
                    if ((left as MathPair).visited) return mp
                    return (left as MathPair).setLeft(mp)
                } else {
                    this.left = (this.left as Int) + (mp.left as Int)
                    mp.left = -1
                    return mp
                }
            } else {
                if ((mp.left as Int) > 0) {
                    if (left is MathPair) {
                        if ((left as MathPair).visited) {
                            this.visited = true
                            return mp
                        }
                        return (left as MathPair).setLeft(mp)
                    } else {
                        this.left = (this.left as Int) + (mp.left as Int)
                        mp.left = -1
                    }
                }
                if ((mp.right as Int) > 0) {
                    if (right is MathPair) {
                        if ((right as MathPair).visited) {
                            this.visited = true
                            return mp
                        }
                        return (right as MathPair).setRight(mp)
                    } else {
                        this.right = (this.right as Int) + (mp.right as Int)
                        mp.right = -1
                    }
                }
            }
        }
        return mp
    }

    private fun setLeft(mp: MathPair): MathPair {
        return if (right is MathPair) {
            (right as MathPair).setLeft(mp)
        } else {
            right = right as Int + mp.left as Int
            mp.left = -1
            mp
        }
    }

    private fun setRight(mp: MathPair): MathPair {
        return if (left is MathPair) {
            (left as MathPair).setRight(mp)
        } else {
            left = left as Int + mp.right as Int
            mp.right = -1
            mp
        }
    }

    fun split(): Boolean {
        if (left is MathPair) {
            if ((left as MathPair).split()) {
                return true
            }
        } else {
            val value = left as Int
            if (value > 9) {
                val mp = MathPair()
                mp.setLeft(Math.floorDiv(value, 2))
                mp.setRight(value - Math.floorDiv(value, 2))
                left = mp
                return true
            }
        }
        if (right is MathPair) {
            if ((right as MathPair).split()) {
                return true
            }
        } else {
            val value = right as Int
            if (value > 9) {
                val mp = MathPair()
                mp.setLeft(Math.floorDiv(value, 2))
                mp.setRight(value - Math.floorDiv(value, 2))
                right = mp
                return true
            }
        }
        return false
    }

    private fun setLeft(value: Int?) {
        left = value!!
    }

    private fun setRight(value: Int?) {
        right = value!!
    }

    fun getMagnitude(): BigInteger {
        var bi = BigInteger.ZERO
        bi = if (left is MathPair) {
            val rightBi = (left as MathPair).getMagnitude()
            bi.add(rightBi.multiply(BigInteger("3")))
        } else {
            val leftBi = BigInteger("" + left)
            bi.add(leftBi.multiply(BigInteger("3")))
        }
        bi = if (right is MathPair) {
            val rightBi = (right as MathPair).getMagnitude()
            bi.add(rightBi.multiply(BigInteger("2")))
        } else {
            val rightBi = BigInteger("" + right)
            bi.add(rightBi.multiply(BigInteger("2")))
        }
        return bi
    }

    fun maxDepth(): Int {
        var currentDepth = depth
        if (left is MathPair) {
            val leftDepth = (left as MathPair).maxDepth()
            if (leftDepth > currentDepth) {
                currentDepth = leftDepth
            }
        }
        if (right is MathPair) {
            val rightDepth = (right as MathPair).maxDepth()
            if (rightDepth > currentDepth) {
                currentDepth = rightDepth
            }
        }
        return currentDepth
    }

    fun maxNum(): Int {
        var currentNum = 0
        if (left is MathPair) {
            val leftDepth = (left as MathPair).maxNum()
            if (leftDepth > currentNum) {
                currentNum = leftDepth
            }
        } else {
            if (left as Int > currentNum) {
                currentNum = left as Int
            }
        }
        if (right is MathPair) {
            val rightDepth = (right as MathPair).maxNum()
            if (rightDepth > currentNum) {
                currentNum = rightDepth
            }
        } else {
            if (right as Int > currentNum) {
                currentNum = right as Int
            }
        }
        return currentNum
    }
}

private fun String.splitTerms(): String {
    return if (this.startsWith("[")) {
        var spl = 0
        val myChars = this.toList()
        var balance = 1

        for (split in 1 until this.length) {
            if ('[' == myChars[split]) balance++
            else if (']' == myChars[split]) balance--
            if (balance == 0) {
                spl = split; break
            }
        }
        this.substring(0, spl + 1) + "|" + this.substring(spl + 2)
    } else {
        this.replaceFirst(",", "|")
    }
}
