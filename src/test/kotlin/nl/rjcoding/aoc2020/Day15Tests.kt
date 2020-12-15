package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Tests {

    @Test
    fun numbers() {
        assertEquals(436, Day15.solve(listOf(0, 3, 6), 2020))
        assertEquals(175594, Day15.solve(listOf(0, 3, 6), 30000000))
    }
}