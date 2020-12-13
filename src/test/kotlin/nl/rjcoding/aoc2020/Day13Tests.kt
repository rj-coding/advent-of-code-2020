package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Day13.earliest
import nl.rjcoding.aoc2020.Day13.search
import nl.rjcoding.aoc2020.Day13.parse
import nl.rjcoding.aoc2020.Util.linearSeq
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Tests {

    val input = """
        939
        7,13,x,x,59,x,31,19
    """.trimIndent().lines().let(::parse)

    @Test
    fun earliest() {
        val result = input.let { (timestamp, ids) -> earliest(timestamp, ids) }
        assertEquals(295, result)
    }

    @Test
    fun search() {
        val result = search(listOf(1789,37,47,1889).mapIndexed { i, v -> i to v.toLong() })
        assertEquals(1202161486, result)
    }
}