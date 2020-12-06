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

    fun groups(input: String): List<String> = input.split("\n\n")

    fun union(group: String): Long = group
        .split("\n")
        .fold(setOf<Char>()) { set, line ->
            set.union(line.toCharArray().toSet())
        }.size.toLong()

    fun intersect(group: String): Long = group
        .split("\n")
        .fold(('a'..'z').toSet()) { set, line ->
            set.intersect(line.toCharArray().toSet())
        }.size.toLong()
}