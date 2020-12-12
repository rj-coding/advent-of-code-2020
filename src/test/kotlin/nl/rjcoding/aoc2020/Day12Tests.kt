package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Tests {

    val input = """
        F10
        N3
        F7
        R90
        F11
    """.trimIndent().lineSequence()

    @Test
    fun navigate() {
        val commands = Day12.parse(input)
        val result = Day12.navigate(commands, Day12.State(0, 0, 1, 0), mode = 0)
        assertEquals(25, result.distance)
    }

    @Test
    fun navigate2() {
        val commands = Day12.parse(input)
        val result = Day12.navigate(commands, Day12.State(0, 0, 10, 1), mode = 1)
        assertEquals(286, result.distance)
    }
}