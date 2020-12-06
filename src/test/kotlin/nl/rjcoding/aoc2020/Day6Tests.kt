package nl.rjcoding.aoc2020

import org.junit.Test
import kotlin.test.assertEquals

class Day6Tests {

    val input = """
        abc

        a
        b
        c

        ab
        ac

        a
        a
        a
        a

        b
    """.trimIndent()

    @Test
    fun union() {
        val groups = Day6.groups(input)
        val counts = groups.map { Day6.union(it) }
        assertEquals(3, counts[0])
        assertEquals(3, counts[1])
        assertEquals(3, counts[2])
        assertEquals(1, counts[3])
        assertEquals(1, counts[4])
    }

    @Test
    fun intersect() {
        val groups = Day6.groups(input)
        val counts = groups.map { Day6.intersect(it) }
        assertEquals(3, counts[0])
        assertEquals(0, counts[1])
        assertEquals(1, counts[2])
        assertEquals(1, counts[3])
        assertEquals(1, counts[4])
    }

}