package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Tests {

    val input1 get() =  """
        16
        10
        15
        5
        1
        11
        7
        19
        6
        12
        4
    """.trimIndent().lineSequence().map { it.toInt() }

    val input2 get() = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent().lineSequence().map { it.toInt() }

    @Test
    fun part1() {
        val result = Day10.part1(input1)
        assertEquals(35, result)
    }

    @Test
    fun part2() {
        val result1 = Day10.part2(input1)
        assertEquals(8, result1)

        val result2 = Day10.part2(input2)
        assertEquals(19208, result2)
    }
}