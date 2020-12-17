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
    fun parse() {
        val cubes = Day17.parse(input)
        val result = cubes.reduceRepeated(6) { Day17.step(it) }
        assertEquals(112, result.size)
    }
}