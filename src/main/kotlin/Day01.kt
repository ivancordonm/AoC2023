fun main() {

    val test1Input = readInput("main/kotlin/Day01_p1_test")
    val test1p2Input = readInput("main/kotlin/Day01_p2_test")
    val input = readInput("main/kotlin/Day01")

    check(part1(test1Input) == 142)
    println(part1(input))

    check(part2(test1p2Input) == 281)
    println(part2(input))


}

fun part1(document: List<String>): Int {
    return document.sumOf { it -> it.filter { it.isDigit() }.let { "${it.first()}${it.last()}".toInt() } }
}

fun part2(document: List<String>): Int {
    val numbers = mapOf(
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    val pattern = numbers.keys.joinToString("|").toRegex()

    //    val result = mutableListOf<Int>()
    //    for (line: String in document) {
    //        val values = mutableListOf<Int>()
    //        for (i in line.length - 1 downTo 0) {
    //            pattern.find(line.substring(i))?.let {
    //                values.add(numbers[it.value]!!)
    //            }
    //        }
    //        result.add("${values.last()}${values.first()}".toInt())
    //    }
    //    return result.sum()

    return document.sumOf { line ->
        line.mapIndexedNotNull { index, _ ->
            pattern.find(line.substring(line.length - 1 - index))?.value
        }.let { "${numbers[it.last()]}${numbers[it.first()]}" }.toInt()
    }

}
