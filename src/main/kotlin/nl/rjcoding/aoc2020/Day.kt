package nl.rjcoding.aoc2020

interface GenericDay<T1, T2> {
    fun part1(): T1
    fun part2(): T2
}

interface Day : GenericDay<Long, Long>