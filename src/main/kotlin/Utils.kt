import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.abs

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

fun readInputByGroups(name: String) = File("src", "$name.txt")
    .readText().split("\r\n\r\n|\n\n".toRegex())

fun readInputToInt(name: String) = File("src", "$name.txt")
    .readLines().map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


fun Pair<Int, Int>.manhattan(other: Pair<Int, Int>) = abs(first - other.first) + abs(second - other.second)

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = Pair(first + other.first, second + other.second)
