package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Tests {

    val input = """
        sesenwnenenewseeswwswswwnenewsewsw
        neeenesenwnwwswnenewnwwsewnenwseswesw
        seswneswswsenwwnwse
        nwnwneseeswswnenewneswwnewseswneseene
        swweswneswnenwsewnwneneseenw
        eesenwseswswnenwswnwnwsewwnwsene
        sewnenenenesenwsewnenwwwse
        wenwwweseeeweswwwnwwe
        wsweesenenewnwwnwsenewsenwwsesesenwne
        neeswseenwwswnwswswnw
        nenwswwsewswnenenewsenwsenwnesesenew
        enewnwewneswsewnwswenweswnenwsenwsw
        sweneswneswneneenwnewenewwneswswnese
        swwesenesewenwneswnwwneseswwne
        enesenwswwswneneswsenwnewswseenwsese
        wnwnesenesenenwwnenwsewesewsesesew
        nenewswnwewswnenesenwnesewesw
        eneswnwswnwsenenwnwnwwseeswneewsenese
        neswnwewnwnwseenwseesewsenwsweewe
        wseweeenwnesenwwwswnew
    """.trimIndent().lineSequence()

    @Test
    fun tileTest() {
        val init = Day24.Tile(0, 0, 0)
        assertEquals(init, init.next(Day24.Direction.E).next(Day24.Direction.W))
        assertEquals(init, init.next(Day24.Direction.NE).next(Day24.Direction.SW))
        assertEquals(init, init.next(Day24.Direction.NW).next(Day24.Direction.SE))
    }

    @Test
    fun flip() {
        val instructions = Day24.parse(input)
        val result = Day24.flip(instructions)
        assertEquals(10, result.size)
    }

    @Test
    fun next() {
        val instructions = Day24.parse(input)
        val init = Day24.flip(instructions)
        val day100 = init.reduceRepeated(100) { Day24.next(it) }
        assertEquals(2208, day100.size)
    }
}