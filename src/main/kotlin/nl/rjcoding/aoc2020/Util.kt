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

    fun linearSeq(start: Int, step: Int) = generateSequence(start) { it + step }
    fun linearSeq(start: Long, step: Long) = generateSequence(start) { it + step }

    val offsets = listOf(-1, 0, 1).flatMap { i -> listOf(-1, 0, 1).map { j -> i to j } }.filter { (i, j) -> !(i == j && i == 0) }
    fun ray(dr: Int, dc: Int) = if (!(dr == dc && dr == 0)) generateSequence(dr to dc) { (i, j) -> (i + dr) to (j + dc) } else emptySequence()
    val rays = offsets.map { (dr, dc) -> ray(dr, dc) }

    fun <T> combinations(seed: Iterable<T>, count: Int): List<List<T>> {

        fun inner(acc: List<List<T>>, remaining: Int): List<List<T>> = when (remaining) {
            0 -> acc
            count ->  inner(seed.map { s -> listOf(s) }, remaining - 1)
            else -> inner(seed.flatMap { s -> acc.map { list -> list + s } }, remaining - 1)
        }

        return inner(emptyList(), count)
    }
}