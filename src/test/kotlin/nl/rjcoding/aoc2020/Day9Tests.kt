package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day9Tests {

    val input = """
        35
        20
        15
        25
        47
        40
        62
        55
        65
        95
        102
        117
        150
        182
        127
        219
        299
        277
        309
        576
    """.trimIndent()

    @Test
    fun isSum() {
        val preamble = (1 .. 25L).toList()
        assertTrue(Day9.isSum(26, preamble))
        assertTrue(Day9.isSum(49, preamble))
        assertFalse(Day9.isSum(100, preamble))
        assertFalse(Day9.isSum(50, preamble))
    }

    @Test
    fun input() {
        val data = input.lineSequence().map { it.toLong() }.windowed(6).first {
            !Day9.isSum(it.last(), it.subList(0, 6))
        }.last()
        assertEquals(127, data)
    }

    @Test
    fun findSet() {
        val data = input.lineSequence().map { it.toLong() }.toList()
        val result = (2 .. data.size)
            .flatMap { data.windowed(it) }
            .first { it.sum() == 127L }
            .minMax().let { (min, max) -> min + max }
        assertEquals(62, result)
    }
}