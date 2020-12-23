package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Tests {

    @Test
    fun play10() {
        val result = Day23.play(389125467L.toDigits(), 9, 10)
        val score = Day23.order(result)
        assertEquals(92658374L, score.toLong())
    }

    @Test
    fun play100() {
        val result = Day23.play(389125467L.toDigits(), 9, 100)
        val score = Day23.order(result)
        assertEquals(67384529L, score.toLong())
    }
}