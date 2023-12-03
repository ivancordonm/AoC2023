package Day03

import readInput
import java.lang.Integer.max
import java.lang.Integer.min

fun main() {

    val testInput = readInput("main/kotlin/Day03/Day03_test")
    val input = readInput("main/kotlin/Day03/Day03")

    check(part1(testInput) == 4361)
    println(part1(input))

    check(part2(testInput) == 467835)
    println(part2(input))

}

fun part1(board: List<String>): Int {

    val pattern = Regex("(\\d+)")
    return board.foldIndexed(0) { index, acc, line ->
        pattern.findAll(line).fold(acc) { acc2, matchResult ->
            if (isPartNumber(board, index, matchResult.range.toList())) acc2 + matchResult.value.toInt()
            else acc2
        }
    }
}


fun isPartNumber(board: List<String>, index: Int, number: List<Int>): Boolean {
    val x = max(index - 1, 0) to min(index + 1, board.lastIndex)
    val y = max(number.first() - 1, 0) to min(number.last() + 1, board[0].lastIndex)
    val subBoard = board.subList(x.first, x.second + 1).map { it.substring(y.first, y.second + 1) }
    return subBoard.any { it.any { it.toString().matches(Regex("[^\\.\\d]")) } }
}

fun part2(board: List<String>) =
    board.foldIndexed(0) { index, acc, s ->
        acc + Regex("(\\*)").findAll(s).map { gearRatio(board, index, it.range.first) }.sum()
    }


fun gearRatio(board: List<String>, row: Int, col: Int): Int {
    val subBoard = board.subList(max(row - 1, 0), min(row + 1, board.lastIndex) + 1)
    val numbers = subBoard.flatMap { "(\\d+)".toRegex().findAll(it).toList() }
    val gears = numbers.filter { it.range.intersect(col - 1..col + 1).isNotEmpty() }.map { it.value.toInt() }
    return if (gears.count() > 1) gears.reduce { acc, i -> acc * i } else 0
}
