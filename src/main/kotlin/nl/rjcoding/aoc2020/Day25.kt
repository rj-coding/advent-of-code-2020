package nl.rjcoding.aoc2020

object Day25 : GenericDay<Long, Unit> {

    val keys = 18356117L to 5909654L

    override fun part1(): Long = keys.let { (card, door) -> solve(card, door) }

    override fun part2(): Unit {}

    fun solve(key1: Long, key2: Long) = generateSequence(1L to 1L) { (acc1, acc2) ->
        (acc1 * 7L) % 20201227 to (acc2 * key2) % 20201227
    }.first { it.first == key1 }.second
}