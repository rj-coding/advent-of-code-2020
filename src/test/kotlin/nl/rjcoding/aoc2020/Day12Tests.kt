package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Constants.j
import nl.rjcoding.aoc2020.Day12.State.Companion.ShipMode
import nl.rjcoding.aoc2020.Day12.State.Companion.WaypointMode
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
        val result = Day12.navigate(commands, Day12.State(Complex.ZERO, Complex.UNIT, ShipMode))
        assertEquals(25, result.distance)
    }

    @Test
    fun navigate2() {
        val commands = Day12.parse(input)
        val result = Day12.navigate(commands, Day12.State(Complex.ZERO, 10 + j, WaypointMode))
        assertEquals(286, result.distance)
    }
}