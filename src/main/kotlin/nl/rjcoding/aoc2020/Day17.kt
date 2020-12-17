package nl.rjcoding.aoc2020

import kotlin.math.max
import kotlin.math.min

object Day17 : Day {

    val input = Util.readInputToLines("day17.txt").let(::parse)

    val neighbors = (-1 until 2).flatMap { dx ->
        (-1 until 2).flatMap { dy ->
            (-1 until 2).map { dz -> Triple(dx, dy, dz) }
        }
    }.filter { (dx, dy, dz) -> !(dx == 0 && dy == 0 && dz ==0) }

    override fun part1(): Long = input.reduceRepeated(6) { step(it) }.size.toLong()

    override fun part2(): Long {
        TODO("Not yet implemented")
    }

    fun parse(input: Sequence<String>): Set<Triple<Int, Int, Int>> = input.foldIndexed(setOf()) { y, set, line ->
        set + line.mapIndexedNotNull { x, c ->
            when (c) {
                '#' -> Triple(x, y, 0)
                else -> null
            }
        }
    }

    fun neighborsOf(cube: Triple<Int, Int, Int>) = neighbors.map { (dx, dy, dz) ->
        Triple(cube.first + dx, cube.second + dy, cube.third + dz)
    }

    fun step(cubes: Set<Triple<Int, Int, Int>>): Set<Triple<Int, Int, Int>> {
        val boundary = mutableSetOf<Triple<Int, Int, Int>>()
        cubes.flatMap { cube -> neighborsOf(cube) }.forEach { n ->
            if (!cubes.contains(n)) boundary.add(n)
        }

        val next = mutableSetOf<Triple<Int, Int, Int>>()
        cubes.forEach { cube ->
            if (neighborsOf(cube).count { n -> cubes.contains(n) } in 2..3) {
                next.add(cube)
            }
        }

        boundary.forEach { inactive ->
            if (neighborsOf(inactive).count { n -> cubes.contains(n) } == 3) {
                next.add(inactive)
            }
        }
        return next
    }

    fun print(cubes: Set<Triple<Int, Int, Int>>): List<String> {
        val (min, max) = cubes.fold(Pair(Int.MAX_VALUE, Int.MAX_VALUE) to Pair(Int.MIN_VALUE, Int.MIN_VALUE)) { (mi, ma), (x, y, _) ->
            Pair(min(mi.first, x), min(mi.second, y)) to Pair(max(ma.first, x), max(ma.second, y))
        }

        return cubes.groupBy { it.third }.let { grouped ->
            grouped.keys.sorted().map { z ->
                (min.second .. max.second).map { y ->
                    (min.first .. max.first).map { x ->
                        when {
                            cubes.contains(Triple(x, y, z)) -> '#'
                            else -> '.'
                        }
                    }.joinToString("")
                }.joinToString("\n")
            }
        }
    }
}