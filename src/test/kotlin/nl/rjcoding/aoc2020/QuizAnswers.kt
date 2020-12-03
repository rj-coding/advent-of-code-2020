package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class QuizAnswers {

    @Test
    fun `Day 1 part 1`() {
        assertEquals(751776L, Day1.part1())
    }

    @Test
    fun `Day 1 part 2`() {
        assertEquals(42275090, Day1.part2())
    }

    @Test
    fun `Day 2 part 1`() {
        assertEquals(603, Day2.part1())
    }

    @Test
    fun `Day 2 part 2`() {
        assertEquals(404, Day2.part2())
    }

    @Test
    fun `Day 3 part 1`() {
        assertEquals(184, Day3.part1())
    }

    @Test
    fun `Day 3 part 2`() {
        assertEquals(2431272960, Day3.part2())
    }
}