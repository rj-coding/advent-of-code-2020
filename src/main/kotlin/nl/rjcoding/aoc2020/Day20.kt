package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Constants.j
import kotlin.math.min

object Day20 : Day {
    override fun part1(): Long {
        TODO("Not yet implemented")
    }

    override fun part2(): Long {
        TODO("Not yet implemented")
    }

    fun parse(input: Sequence<String>): List<Tile> {
        val tiles = mutableListOf<Tile>()
        var currentId = -1
        val acc = mutableListOf<String>()
        input.forEach { line ->
            when {
                line.startsWith("Tile ") -> currentId = line.split(" ")[1].dropLast(1).toInt()
                line.isBlank() -> {
                    tiles.add(Tile.fromString(currentId, acc))
                    acc.clear()
                }
                else -> acc.add(line)
            }
        }
        tiles.add(Tile.fromString(currentId, acc))
        return tiles
    }

    data class Tile(val id: Int, val pixels: Set<Complex>, val width: Int, val height: Int) {

        fun rotateLeft(): Tile = fromPixels(id, pixels.map { it * j }.toSet(), width, height)
        fun rotateRight(): Tile = fromPixels(id, pixels.map { it * -j }.toSet(), width, height)
        fun flipHorizontal(): Tile = fromPixels(id, pixels.map { it.copy(i = -it.i) }.toSet(), width, height)
        fun flipVertical(): Tile = fromPixels(id, pixels.map { it.copy(j = -it.j) }.toSet(), width, height)

        fun variations(): Set<Tile> = listOf(this)
            .reduceRepeated(3) { tiles -> tiles + tiles.last().rotateRight() }
            .flatMap { tile -> listOf(tile, tile.flipHorizontal(), flipVertical()) }
            .toSet()

        fun edge(direction: Direction): Set<Long> = when (direction) {
            Direction.North -> pixels.filter { it.j == (height - 1).toLong() }.map { it.i }.toSet()
            Direction.East -> pixels.filter { it.i == (width - 1).toLong() }.map { it.j }.toSet()
            Direction.South -> pixels.filter { it.j == 0L }.map { it.i }.toSet()
            Direction.West -> pixels.filter { it.i == 0L }.map { it.j }.toSet()
        }

        override fun toString(): String {
            return (0 until height).map { r ->
                (0 until width).map { c -> if (pixels.contains(c + (height - r - 1) * j)) '#' else '.' }.joinToString("")
            }.joinToString("\n")
        }

        companion object {
            fun fromString(id: Int, data: List<String>): Tile {
                val pixels = mutableSetOf<Complex>()
                data.forEachIndexed { r, line ->
                    line.forEachIndexed { c, char ->
                        if (char == '#') {
                            pixels.add(c + (data.size - r - 1) * j)
                        }
                    }
                }
                return Tile(id, pixels, data[0].length, data.size)
            }

            fun fromPixels(id: Int, pixels: Set<Complex>, width: Int, height: Int): Tile {
                val (minX, minY) = pixels.fold(Long.MAX_VALUE to Long.MAX_VALUE) { (minX, minY), c ->
                    min(minX, c.i) to min(minY, c.j)
                }
                return Tile(id, width = width, height = height, pixels = pixels.map { p -> p - (minX + minY * j) }.toSet())
            }
        }

    }
}