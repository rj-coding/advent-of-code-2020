package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Day8.parse
import org.junit.Test
import kotlin.test.assertEquals

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
        val code = input.lineSequence().let(::parse)
        val acc = Day8.run(code)
            .takeWhile { (acc, loop) -> !loop }
            .last().first
        assertEquals(5, acc)
    }

}