package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Tests {

    @Test
    fun eval() {
        assertEquals(3, Day18.eval("(((((1 + 2)))))", false))
        assertEquals(3, Day18.eval("3", false))
        assertEquals(8, Day18.eval("3 + 5", false))
        assertEquals(18, Day18.eval("3 + (5 * 3)", false))
        assertEquals(71, Day18.eval("1 + 2 * 3 + 4 * 5 + 6", false))
        assertEquals(13632, Day18.eval("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", false))
    }

    @Test
    fun eval2() {
        assertEquals(231, Day18.eval("1 + 2 * 3 + 4 * 5 + 6", true))
        assertEquals(51, Day18.eval("1 + (2 * 3) + (4 * (5 + 6))", true))
        assertEquals(46, Day18.eval("2 * 3 + (4 * 5)", true))
        assertEquals(1445, Day18.eval("5 + (8 * 3 + 9 + 3 * 4 * 3)", true))
        assertEquals(669060, Day18.eval("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", true))
        assertEquals(23340, Day18.eval("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", true))
    }
}