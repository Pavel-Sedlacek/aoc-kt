package utils.helpers.y2021

fun Map<String, List<String>>.countPaths(
    cave: String = "start",
    path: List<String> = listOf(),
    isSmallCaveNotAllowed: (cave: String, currentPath: List<String>) -> Boolean
): Int {
    return when {
        cave == "end" -> 1
        cave.first().isLowerCase() && isSmallCaveNotAllowed(cave, path) -> 0
        else -> this.getValue(cave).sumOf { countPaths(it, path + cave, isSmallCaveNotAllowed) }
    }
}