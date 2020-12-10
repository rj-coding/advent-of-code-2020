package nl.rjcoding.aoc2020

object Day10  : Day {

    val input get() = Util.readInputToLines("day10.txt").map { it.toInt() }

    override fun part1(): Long = part1(input).toLong()

    fun part1(input: Sequence<Int>) = input.plus(0).sorted()
        .windowed(2)
        .groupBy { (prev, next) -> next - prev }
        .let { it[1]!!.size * (it[3]!!.size + 1)}

    override fun part2(): Long = part2(input)

    fun part2(input: Sequence<Int>) = input.sorted().toList()
        .let { it + (it.max()!! + 3) }
        .fold(mapOf(0 to 1L)) { lookup, j ->
            lookup + (j to (1 .. 3).map { dj -> lookup[j - dj] ?: 0 }.sum())
        }
        .values.max()!!
}