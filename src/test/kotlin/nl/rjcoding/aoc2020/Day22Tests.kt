package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Tests {

    val input = """
        Player 1:
        9
        2
        6
        3
        1

        Player 2:
        5
        8
        4
        7
        10
    """.trimIndent().lineSequence()

    val loopInput = """
        Player 1:
        43
        19

        Player 2:
        2
        29
        14
    """.trimIndent().lineSequence()

    @Test
    fun combat() {
        val (p1, p2) = Day22.parse(input)
        val (_, winner) = Day22.play(p1, p2, recursive = false)
        val result = Day22.score(winner)
        assertEquals(306, result)
    }

    @Test
    fun recursiveCombat() {
        val (p1, p2) = Day22.parse(input)
        val (_, winner) = Day22.play(p1, p2, recursive = true)
        val result = Day22.score(winner)
        assertEquals(291, result)
    }

    @Test
    fun loop() {
        val (p1, p2) = Day22.parse(loopInput)
        Day22.play(p1, p2, recursive = true)
    }
}