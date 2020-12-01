package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Day1.findTriplesThatSumTo
import nl.rjcoding.aoc2020.Day1.findTuplesThatSumTo
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class Day1Tests {

    @Test
    fun testFindTuplesThatSumTo() {
        val input = sequenceOf(1721L, 979L, 366L, 299L, 675L, 1456L)
        val to = 2020L
        val result = findTuplesThatSumTo(input, to)
        assertNotNull(result)
        assertTrue { (result.first == 1721L && result.second == 299L) || (result.first == 299L && result.second == 1721L)  }
    }

    @Test
    fun testFindTriplesThatSumTo() {
        val input = sequenceOf(1721L, 979L, 366L, 299L, 675L, 1456L)
        val to = 2020L
        val result = findTriplesThatSumTo(input, to)
        assertNotNull(result)
        val set = setOf(result.first, result.second, result.third)
        assertTrue { set.containsAll(listOf(979L, 366L, 675L)) }
    }
}