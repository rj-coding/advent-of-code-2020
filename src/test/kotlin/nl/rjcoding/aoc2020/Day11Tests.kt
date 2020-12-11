package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Day11.answer
import nl.rjcoding.aoc2020.Day11.parse
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Tests {

    val input = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
    """.trimIndent().lineSequence().let(::parse)


    @Test
    fun updatePart1() {
        val maps = Day11.updates(input, 1)
        val result = answer(maps)
        assertEquals(37, result)
    }

    @Test
    fun updatePart2() {
        val maps = Day11.updates(input, 2)
        val result = answer(maps)
        assertEquals(26, result)
    }
}