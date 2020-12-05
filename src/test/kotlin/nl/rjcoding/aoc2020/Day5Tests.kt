package nl.rjcoding.aoc2020

import org.junit.Test
import kotlin.test.assertEquals

class Day5Tests {

    @Test
    fun seatId() {
        val seat1 = Day5.parse("FBFBBFFRLR")
        assertEquals(44 * 8 + 5, seat1)

        val seat2 = Day5.parse("BFFFBBFRRR")
        assertEquals(70 * 8 + 7, seat2)

        val seat3 = Day5.parse("FFFBBBFRRR")
        assertEquals(14 * 8 + 7, seat3)

        val seat4 = Day5.parse("BBFFBBFRLL")
        assertEquals(102 * 8 + 4, seat4)
    }

}