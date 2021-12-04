import java.io.File

fun main() {
    for (it in 0 until 25) {
        val z = File("src/main/kotlin/y2022/Day${(it + 1).toString().padStart(2, '0')}.kt")
        z.createNewFile()
        z.writeText(
            "package y2022\n" +
                    "\n"+
                    "import utils.Day\n" +
                    "\n"+
                    "class Day${(it + 1).toString().padStart(2, '0')} : Day<Int> {\n" +
                    "\n" +
                    "    val input = file\n" +
                    "\n" +
                    "    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })\n" +
                    "\n" +
                    "    private fun partOne(input: String): Int {\n" +
                    "       return 0 \n" +
                    "    }\n" +
                    "\n" +
                    "    private fun partTwo(input: String): Int {\n" +
                    "       return 0 \n" +
                    "    }\n" +
                    "}"
        )
    }
}