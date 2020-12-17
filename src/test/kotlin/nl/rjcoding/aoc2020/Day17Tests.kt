package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Tests {

    val input = """
        .#.
        ..#
        ###
    """.trimIndent().lineSequence().toList()

    @Test
    fun stepPart1() {
        val cubes = Day17.parse(input, 3)
        val result = cubes.reduceRepeated(6) { Day17.step(it) }
        assertEquals(112, result.size)
    }

    @Test
    fun stepPart2() {
        val cubes = Day17.parse(input, 4)
        val result = cubes.reduceRepeated(6) { Day17.step(it) }
        assertEquals(848, result.size)
    }
}