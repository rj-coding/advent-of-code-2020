package nl.rjcoding.aoc2020

import org.junit.Test
import kotlin.test.assertEquals

class Day5Tests {

    @Test
    fun rowRegion() {
        var row = Day5.Region.row
        assertEquals(0, row.min)
        assertEquals(127, row.max)

        row = row.lower()
        assertEquals(0, row.min)
        assertEquals(63, row.max)

        row = row.upper()
        assertEquals(32, row.min)
        assertEquals(63, row.max)

        row = row.lower()
        assertEquals(32, row.min)
        assertEquals(47, row.max)

        row = row.upper()
        assertEquals(40, row.min)
        assertEquals(47, row.max)

        row = row.upper()
        assertEquals(44, row.min)
        assertEquals(47, row.max)

        row = row.lower()
        assertEquals(44, row.min)
        assertEquals(45, row.max)

        row = row.lower()
        assertEquals(44, row.min)
        assertEquals(44, row.max)
    }

    @Test
    fun seatId() {
        val seat1 = Day5.parse("FBFBBFFRLR")
        assertEquals(44, seat1.row.value())
        assertEquals(5, seat1.col.value())

        val seat2 = Day5.parse("BFFFBBFRRR")
        assertEquals(70, seat2.row.value())
        assertEquals(7, seat2.col.value())

        val seat3 = Day5.parse("FFFBBBFRRR")
        assertEquals(14, seat3.row.value())
        assertEquals(7, seat3.col.value())

        val seat4 = Day5.parse("BBFFBBFRLL")
        assertEquals(102, seat4.row.value())
        assertEquals(4, seat4.col.value())
    }

}