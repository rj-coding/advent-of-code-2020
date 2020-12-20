package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Tests {

    val input = """
        Tile 2311:
        ..##.#..#.
        ##..#.....
        #...##..#.
        ####.#...#
        ##.##.###.
        ##...#.###
        .#.#.#..##
        ..#....#..
        ###...#.#.
        ..###..###

        Tile 1951:
        #.##...##.
        #.####...#
        .....#..##
        #...######
        .##.#....#
        .###.#####
        ###.##.##.
        .###....#.
        ..#.#..#.#
        #...##.#..

        Tile 1171:
        ####...##.
        #..##.#..#
        ##.#..#.#.
        .###.####.
        ..###.####
        .##....##.
        .#...####.
        #.##.####.
        ####..#...
        .....##...

        Tile 1427:
        ###.##.#..
        .#..#.##..
        .#.##.#..#
        #.#.#.##.#
        ....#...##
        ...##..##.
        ...#.#####
        .#.####.#.
        ..#..###.#
        ..##.#..#.

        Tile 1489:
        ##.#.#....
        ..##...#..
        .##..##...
        ..#...#...
        #####...#.
        #..#.#.#.#
        ...#.#.#..
        ##.#...##.
        ..##.##.##
        ###.##.#..

        Tile 2473:
        #....####.
        #..#.##...
        #.##..#...
        ######.#.#
        .#...#.#.#
        .#########
        .###.#..#.
        ########.#
        ##...##.#.
        ..###.#.#.

        Tile 2971:
        ..#.#....#
        #...###...
        #.#.###...
        ##.##..#..
        .#####..##
        .#..####.#
        #..#.#..#.
        ..####.###
        ..#.#.###.
        ...#.#.#.#

        Tile 2729:
        ...#.#.#.#
        ####.#....
        ..#.#.....
        ....#..#.#
        .##..##.#.
        .#.####...
        ####.#.#..
        ##.####...
        ##..#.##..
        #.##...##.

        Tile 3079:
        #.#.#####.
        .#..######
        ..#.......
        ######....
        ####.#..#.
        .#...#.##.
        #.#####.##
        ..#.###...
        ..#.......
        ..#.###...
    """.trimIndent().lineSequence()

    @Test
    fun parse() {
        val tiles = Day20.parse(input)
        assertEquals(9, tiles.size)
    }

    @Test
    fun rotate() {
        val tiles = Day20.parse(input)
        val r0 = tiles[0].rotateRight().rotateRight().rotateRight().rotateRight()
        assertEquals(tiles[0], r0)
        val r1 = r0.rotateLeft().rotateLeft().rotateLeft().rotateLeft()
        assertEquals(tiles[0], r1)
    }

    @Test
    fun flip() {
        val tiles = Day20.parse(input)
        val f0 = tiles[0].flipHorizontal().flipHorizontal()
        val f1 = tiles[0].flipVertical().flipVertical()
        assertEquals(tiles[0], f0)
        assertEquals(f0, f1)
    }

    @Test
    fun edge() {
        val tiles = Day20.parse(input)
        val n = tiles[0].edge(Direction.North)
        val e = tiles[0].edge(Direction.East)
        val s = tiles[0].edge(Direction.South)
        val w = tiles[0].edge(Direction.West)
        println()
    }
}