package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Tests {

    @Test
    fun loop() {
        val key = Day25.solve(5764801, 17807724)
        assertEquals(14897079, key)
    }
}