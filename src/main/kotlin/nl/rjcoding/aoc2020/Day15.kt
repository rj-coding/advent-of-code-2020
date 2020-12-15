package nl.rjcoding.aoc2020

object Day15 : Day {
    override fun part1(): Long = numbers(listOf(2,1,10,11,0,6)).take(2020).last()

    override fun part2(): Long = numbers(listOf(2,1,10,11,0,6)).take(30000000).last()

    fun numbers(start: List<Long>): Sequence<Long> = sequence {
        val history = mutableMapOf<Long, Pair<Long, Long>>()
        start.forEachIndexed { i, n ->
            history[n] = i.toLong() to i.toLong()
            yield(n)
        }

        var last = start.last()
        var i = start.size.toLong()
        while (true) {
            history[last]!!.also { (a, b) ->
                last = b - a
                history[last]
                    ?.also { (_, c) -> history[last] = c to i }
                    ?: run { history[last] = i to i }
            }
            yield(last)
            i++
        }
    }
}