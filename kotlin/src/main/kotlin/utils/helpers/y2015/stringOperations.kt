package utils.helpers.y2015

fun String.memorySize(): Int =
    this.replace("\\\\", "*").replace("\\\"", "*").replace("\\\\x[0-9a-f]{2}".toRegex(), "*").length

fun String.pureLength(): Int {
    return StringBuilder("\\\"").also {
        for (i in this) it.append("${if (i == '\\' || i == '\"') '\\' else ""}$i")
        it.append("\\\"")
    }.length
}