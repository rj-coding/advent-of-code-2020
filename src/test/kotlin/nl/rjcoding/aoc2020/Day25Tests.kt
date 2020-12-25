package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Tests {

    @Test
    fun loop() {
       val loop1 = Day25.loop(5764801)
       assertEquals(8, loop1)

        val loop2 = Day25.loop(17807724)
        assertEquals(11, loop2)

        val key1 = Day25.transform(17807724, loop1)
        val key2 = Day25.transform(5764801, loop2)
        assertEquals(key1, key2)
    }
}