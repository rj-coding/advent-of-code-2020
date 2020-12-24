package nl.rjcoding.aoc2020

object Day24 : Day {

    val directions = setOf("e", "se", "sw", "w", "nw", "ne")
    val input = flip(Util.readInputToLines("day24.txt").let(::parse))

    override fun part1(): Long = input.size.toLong()

    override fun part2(): Long = input.reduceRepeated(100) { next(it) }.size.toLong()

    fun parse(sequence: Sequence<String>): List<List<String>> = sequence.map { line ->
        val instruction = mutableListOf<String>()
        var acc = ""
        line.forEach { c ->
            acc += c
            if (this.directions.contains(acc)) {
                instruction.add(acc)
                acc = ""
            }
        }
        instruction
    }.toList()

    fun flip(instructions: List<List<String>>): Set<Tile> {
        val flipped = mutableSetOf<Tile>()
        instructions.forEach { instruction ->
            val destination = instruction.fold(Tile(0, 0, 0)) { tile, d -> tile.next(d) }
            if (!flipped.contains(destination)) flipped.add(destination) else flipped.remove(destination)
        }
        return flipped
    }

    fun next(tiles: Set<Tile>): Set<Tile> {
        val flipped = mutableSetOf<Tile>()
        val boundary = hashMapOf<Tile, Int>()
        val neighbors = hashMapOf<Tile, Int>()
        tiles.map { it to it.neighbors() }.forEach { (tile, ns) ->
            ns.filter { !tiles.contains(it) }.forEach { emptyTile ->
                boundary[emptyTile] = boundary.getOrDefault(emptyTile, 0) + 1
            }
            neighbors[tile] = ns.filter { tiles.contains(it) }.size
        }

        // rule 1
        tiles.filter { tile ->
            neighbors[tile]!!.let { it != 0 && it <= 2 }
        }.also { flipped.addAll(it) }

        // rule 2
        boundary.forEach { tile, neighbors ->
            if (neighbors == 2) flipped.add(tile)
        }

        return flipped
    }

    data class Tile(val x: Int, val y: Int, val z: Int) {
        fun next(direction: String): Tile = when (direction) {
            "e" -> copy(x = x + 1, y = y - 1)
            "se" -> copy(y = y - 1, z = z + 1)
            "sw" -> copy(x = x - 1, z = z + 1)
            "w" -> copy(x = x - 1, y = y + 1)
            "nw" -> copy(y = y + 1, z = z - 1)
            "ne" -> copy(x = x + 1, z = z - 1)
            else -> this
        }

        fun neighbors(): Set<Tile> = Day24.directions.map { next(it) }.toSet()
    }
}