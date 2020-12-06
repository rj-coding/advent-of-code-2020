package nl.rjcoding.aoc2020

object Day6 : Day {

    override fun part1(): Long = Util.readInput("day6.txt")
        .let(::groups)
        .map(::union)
        .sum()

    override fun part2(): Long = Util.readInput("day6.txt")
        .let(::groups)
        .map(::intersect)
        .sum()

    fun groups(input: String): List<List<Set<Char>>> = input
        .split("\n\n")
        .map { it.split("\n").map { it.toSet() } }

    fun union(groups: List<Set<Char>>): Long = groups
        .reduce { a, b -> a union b }.size.toLong()

    fun intersect(groups: List<Set<Char>>): Long = groups
        .reduce { a, b -> a intersect b }.size.toLong()
}