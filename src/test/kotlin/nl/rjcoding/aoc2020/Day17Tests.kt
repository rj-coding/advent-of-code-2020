package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Tests {

    val input = """
        .#.
        ..#
        ###
    """.trimIndent().lineSequence()

    @Test
    fun stepPart1() {
        val cubes = Day17.parse(input)
        val result = cubes.reduceRepeated(6) { Day17.step(it, 1) }
        assertEquals(112, result.size)
    }

    @Test
    fun stepPart2() {
        val cubes = Day17.parse(input)
        val result = cubes.reduceRepeated(6) { Day17.step(it, 2) }
        assertEquals(848, result.size)
    }
}