package Day02

import readInput
import kotlin.math.max

fun main() {

    val testInput = readInput("main/kotlin/Day02/Day02_test")
    val input = readInput("main/kotlin/Day02/Day02")

    check(part1(testInput) == 8)
    println(part1(input))

    check(part2(testInput) == 2286)
    println(part2(input))
}

fun part1(board: List<String>) = board.foldIndexed(0) { index, acc, s ->
    val sets = s.split(":").last().trim().split(";").map { it.getSet().isPossible() }
    acc + if (sets.contains(true)) 0 else index + 1
}

fun part2(board: List<String>) = board.sumOf { s ->
    s.split(":").last().trim().split(";").map { it.getSet() }.power()
}

fun List<Map<String, Int>>.power() = fold(mapOf("red" to 0, "green" to 0, "blue" to 0)) { acc, map ->
    acc.mapValues { (k, v) -> max(v, map.getValue(k)) }
}.values.reduce { acc, i -> acc * i }

fun Map<String, Int>.isPossible() = getValue("red") > 12 || getValue("green") > 13 || getValue("blue") > 14

fun String.getSet() =
    trim().split(",").associate { set -> set.trim().split(" ").let { it[1] to it[0].toInt() } }
        .withDefault { 0 }
