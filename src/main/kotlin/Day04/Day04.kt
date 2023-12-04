package Day04

import readInput
import kotlin.math.min
import kotlin.math.pow

fun main() {

    val testInput = readInput("main/kotlin/Day04/Day04_test")
    val input = readInput("main/kotlin/Day04/Day04")

    check(part1(testInput) == 13)
    println(part1(input))

    check(part2(testInput) == 30)
    println(part2(input))

}

fun getTabBoard(board: List<String>) = board.map { card ->
    card.split(":", "|")
        .let { listOf(it[1].trim().split("\\s+".toRegex()).toSet(), it[2].trim().split("\\s+".toRegex()).toSet()) }
}

fun part1(board: List<String>): Int {
    val tabBoard = getTabBoard(board)
    return tabBoard.fold(0) { acc, card ->
        val hits = card.first().intersect(card.last()).count()
        acc + 2.0.pow(hits - 1).toInt()
    }
}

fun part2(board: List<String>): Int {
    val tabBoard = getTabBoard(board)
    val instances = MutableList(tabBoard.size) { 1 }
    tabBoard.forEachIndexed { index, card ->
        val hits = card.first().intersect(card.last()).count()
        if (index < board.lastIndex)
            for (i in index + 1..min(board.lastIndex, index + hits)) {
                instances[i] = instances[i] + instances[index]
            }
    }
    return instances.sum()
}