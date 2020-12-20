package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Constants.j
import java.util.*
import kotlin.math.sqrt

object Day20 : Day {
    override fun part1(): Long = Util.readInputToLines("day20.txt").let(::parse).let(::reconstruct)
        .corners().map { it.id }.reduce { l, r -> l * r }

    override fun part2(): Long {
        TODO("Not yet implemented")
    }

    fun parse(input: Sequence<String>): List<Tile> {
        val tiles = mutableListOf<Tile>()
        var currentId = -1L
        val acc = mutableListOf<String>()
        input.forEach { line ->
            when {
                line.startsWith("Tile ") -> currentId = line.split(" ")[1].dropLast(1).toLong()
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

    fun reconstruct(tiles: List<Tile>): Image {
        val images = Stack<Image>()
        val dim = sqrt(tiles.size.toDouble()).toInt()

        images.add(Image(dim, dim, listOf(), tiles.toSet()))

        var answer: Image? = null
        while (answer == null) {
            val image = images.pop()
            if (image.isComplete) {
                answer = image
            } else {
                images.addAll(image.next())
            }
        }
        return answer
    }

    data class Tile(val id: Long, val pixels: Set<Complex>, val width: Int, val height: Int) {

        fun rotateLeft(): Tile = Tile(id, pixels.map { it * j + height }.toSet(), height, width)
        fun rotateRight(): Tile = Tile(id, pixels.map { it * -j + (width - 1) * j }.toSet(), height, width)
        fun flipHorizontal(): Tile = Tile(id, pixels.map { it.copy(i = -it.i + (width - 1)) }.toSet(), width, height)
        fun flipVertical(): Tile = Tile(id, pixels.map { it.copy(j = -it.j + (height - 1)) }.toSet(), width, height)

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
            fun fromString(id: Long, data: List<String>): Tile {
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
        }

    }

    data class Image(val width: Int, val height: Int, val tiles: List<Tile>, val options: Set<Tile>) {
        val isComplete = tiles.size == width * height
        val isValid = (!isComplete && options.isNotEmpty()) || isComplete

        fun corners(): List<Tile> = listOf(0, width -1, width * (height - 1), width * height - 1)
            .mapNotNull { tiles.getOrNull(it) }

        fun next(): List<Image> {
            val idx = tiles.size
            val (row, col) = idx / height to idx % height
            val edges = mutableSetOf<Pair<Direction, Set<Long>>>()

            if (row > 0) edges.add(Direction.North to tiles[idx - width].edge(Direction.South))
            if (col > 0) edges.add(Direction.West to tiles[idx - 1].edge(Direction.East))

            val matches = when (idx) {
                0 -> options.flatMap { it.variations() }
                else -> options.mapNotNull { tile ->
                    tile.variations().firstOrNull{ variation -> edges.all { (direction, edge) -> variation.edge(direction) == edge } }
                }
            }

            return matches
                .map { tile -> copy(tiles = tiles + tile, options = options - options.filter { it.id == tile.id }) }
                .filter { image -> image.isValid }
        }
    }
}