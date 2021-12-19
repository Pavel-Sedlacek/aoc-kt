package utils.helpers.y2017

tailrec fun jump(offsets: MutableList<Int>, mutator: (Int) -> Int, pc: Int = 0, steps: Int = 0): Int =
    if (pc < 0 || pc >= offsets.size) steps
    else {
        val nextPc = pc + offsets[pc]
        offsets[pc] += mutator(offsets[pc])
        jump(offsets, mutator, nextPc, steps.inc())
    }