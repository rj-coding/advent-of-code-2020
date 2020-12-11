package nl.rjcoding.aoc2020

object Util {

    fun readInput(fileName: String): String {
        return javaClass.getResource("/${fileName}").readText()
    }

    fun readInputToLines(fileName: String): Sequence<String> {
        return javaClass.getResourceAsStream("/${fileName}")!!.bufferedReader().lineSequence()
    }
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

fun offsets(): List<Pair<Int, Int>> = listOf(-1, 0, 1).flatMap { i -> listOf(-1, 0, 1).map { j -> i to j } }.filter { (i, j) -> !(i == j && i == 0) }
fun ray(dr: Int, dc: Int) = if (!(dr == dc && dr == 0)) generateSequence(dr to dc) { (i, j) -> (i + dr) to (j + dc) } else emptySequence()
fun rays() = offsets().map { (dr, dc) -> ray(dr, dc) }