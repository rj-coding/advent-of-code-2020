package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Tests {

    val input = """
        mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
        mem[8] = 11
        mem[7] = 101
        mem[8] = 0
    """.trimIndent().lineSequence()

    val input2 = """
        mask = 000000000000000000000000000000X1001X
        mem[42] = 100
        mask = 00000000000000000000000000000000X0XX
        mem[26] = 1
    """.trimIndent().lineSequence()

    @Test
    fun evaluate1() {
        val commands = Day14.parse(input).toList()
        val result = Day14.evaluate(commands, 1)
        assertEquals(165, result)
    }

    @Test
    fun evaluate2() {
        val commands = Day14.parse(input2).toList()
        val result = Day14.evaluate(commands, 2)
        assertEquals(208, result)
    }
}