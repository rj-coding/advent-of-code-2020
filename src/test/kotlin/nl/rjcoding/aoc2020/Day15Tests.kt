package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Tests {

    @Test
    fun numbers() {
        assertEquals(436, Day15.numbers(listOf(0L, 3L, 6L)).take(2020).last())
        //assertEquals(175594, Day15.numbers(listOf(0L, 3L, 6L)).take(30000000).last())
    }
}