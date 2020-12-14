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
    fun mask() {
        val mask = Day14.Command.Mask.fromString("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        assertEquals(73, mask(11))
        assertEquals(101, mask(101))
        assertEquals(64, mask(0))
    }

    @Test
    fun evaluate1() {
        val commands = Day14.parse(input).toList()
        val result = commands
            .fold(Day14.State.INIT) { state, command -> state.update(command, 1) }
            .memory.values.sum()
        assertEquals(165, result)
    }

    @Test
    fun evaluate2() {
        val commands = Day14.parse(input2).toList()
        val result = commands
            .fold(Day14.State.INIT) { state, command -> state.update(command, 2) }
            .memory.values.sum()
        assertEquals(208, result)
    }
}