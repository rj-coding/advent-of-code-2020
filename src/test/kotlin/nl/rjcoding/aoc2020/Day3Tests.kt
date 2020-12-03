package nl.rjcoding.aoc2020

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class Day3Tests {

    val input = """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
    """.trimIndent().lines()

    @Test
    fun mapTest() {
        val map = Day3.Map(input)
        val asserts = listOf(
            (0 to 0) to false,
            (2 to 0) to true,
            (0 to 1) to true,
            (11 to 1) to true,
            (10 to 10) to true,
            (12 to 10) to true
        )

        asserts.forEach { (pos, expected) ->
            val (x, y) = pos
            assertEquals(expected, map.isTree(x, y), "Position $x, $y was supposed to be $expected")
        }
    }

    @Test
    fun howManyTreesHitTest() {
        val map = Day3.Map(input)
        val hits = Day3.howManyTreesHit(map, 3, 1)
        assertEquals(7, hits)
    }
}