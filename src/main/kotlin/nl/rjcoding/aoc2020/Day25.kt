package nl.rjcoding.aoc2020

object Day25 : GenericDay<Long, Unit> {

    val keys = 18356117L to 5909654L

    override fun part1(): Long = keys.let { (card, door) -> transform(door, loop(card)) }

    override fun part2(): Unit {}

    fun loop(key: Long): Int = sequence {
        var acc = 1L
        var i = 0
        while (true) {
            yield(i to acc)
            acc *= 7L
            acc %= 20201227
            i += 1
        }
    }.first { it.second == key }.first

    fun transform(key: Long, loop: Int): Long {
        return (1L).reduceRepeated(loop) { acc -> (acc * key) % 20201227 }
    }
}