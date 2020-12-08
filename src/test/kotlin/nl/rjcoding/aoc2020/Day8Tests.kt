package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Day8.parseLine
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day8Tests {

    val input = """
        nop +0
        acc +1
        jmp +4
        acc +3
        jmp -3
        acc -99
        acc +1
        jmp -4
        acc +6
    """.trimIndent()

    val inputCorrect = """
        nop +0
        acc +1
        jmp +4
        acc +3
        jmp -3
        acc -99
        acc +1
        nop -4
        acc +6
    """.trimIndent()

    @Test
    fun execute() {
        val code = input.lineSequence().map(::parseLine).toList()
        val states = Day8.execute(code)

        val result = states.take(8).toList()
        assertEquals(1, result[1].currentLine)
        assertEquals(1, result[7].currentLine)
    }

    @Test
    fun takeUntil() {
        val code = input.lineSequence().map(::parseLine).toList()
        val states = Day8.execute(code)

        val result = Day8.takeUntilLoop(states).last()
        assertEquals(5, result.acc)
    }

    @Test
    fun isCorrupt() {
        val corruptCode = input.lineSequence().map(::parseLine).toList()
        val correctCode = inputCorrect.lineSequence().map(::parseLine).toList()

        assertTrue(Day8.isCorrupt(corruptCode))
        assertFalse(Day8.isCorrupt(correctCode))
    }

    @Test
    fun fix() {
        val corruptCode = input.lineSequence().map(::parseLine).toList()
        val correctCode = Day8.fix(corruptCode)!!
        val states = Day8.execute(correctCode)
        val result = states.last()
        assertEquals(8, result.acc)
    }

}