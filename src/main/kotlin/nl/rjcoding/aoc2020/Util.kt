package nl.rjcoding.aoc2020

object Util {

    fun readInput(fileName: String): String {
        return javaClass.getResource("/${fileName}").readText()
    }

    fun readInputToLines(fileName: String): Sequence<String> {
        return javaClass.getResourceAsStream("/${fileName}")!!.bufferedReader().lineSequence()
    }

    fun readInputRegex(fileName: String, regex: Regex): Sequence<List<String>> =
        readInputRegex(readInputToLines(fileName), regex)

    fun readInputRegex(lines: Sequence<String>, regex: Regex): Sequence<List<String>> = lines
        .mapIndexed { i, line ->
            require(regex.matches(line)) {"$regex doesn't match line $i: $line"}
            regex.matchEntire(line)!!.groupValues
        }

    val offsets = listOf(-1, 0, 1).flatMap { i -> listOf(-1, 0, 1).map { j -> i to j } }.filter { (i, j) -> !(i == j && i == 0) }
    fun ray(dr: Int, dc: Int) = if (!(dr == dc && dr == 0)) generateSequence(dr to dc) { (i, j) -> (i + dr) to (j + dc) } else emptySequence()
    val rays = offsets.map { (dr, dc) -> ray(dr, dc) }
}

fun <T> Sequence<T>.takeWhileInclusive(pred: (T) -> Boolean): Sequence<T> {
    var shouldContinue = true
    return takeWhile {
        val result = shouldContinue
        shouldContinue = pred(it)
        result
    }
}

fun Collection<Long>.minMax(): Pair<Long, Long> = this.fold(Long.MAX_VALUE to Long.MIN_VALUE) { (min, max), v ->
    kotlin.math.min(min, v) to kotlin.math.max(max, v)
}

data class Complex(val i: Long = 0, val j: Long = 0) {

    constructor(i: Int, j: Int): this(i.toLong(), j.toLong())

    operator fun plus(other: Complex) = Complex(i + other.i, j + other.j)
    operator fun plus(pair: Pair<Long, Long>) = Complex(i + pair.first, j + pair.second)

    operator fun minus(other: Complex) = Complex(i - other.i, j - other.j)
    operator fun minus(pair: Pair<Long, Long>) = Complex(i - pair.first, j - pair.second)

    operator fun times(other: Complex) = Complex(i * other.i - j * other.j, i * other.j + j * other.i)
    operator fun times(pair: Pair<Long, Long>) = Complex(i * pair.first - j * pair.second, i * pair.second + j * pair.first)
    operator fun times(s: Long) = Complex(i * s, j * s)
    operator fun times(s: Int) = Complex(i * s, j * s)
}