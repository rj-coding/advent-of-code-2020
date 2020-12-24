package nl.rjcoding.aoc2020

object Day24 : Day {

    val input = Util.readInputToLines("day24.txt").let(::parse)

    override fun part1(): Long = flip(input).size.toLong()

    override fun part2(): Long = flip(input).reduceRepeated(100) { next(it) }.size.toLong()

    fun parse(sequence: Sequence<String>): List<List<Direction>> = sequence.map { line ->
        val directions = mutableListOf<Direction>()
        var acc = ""
        line.forEach { c ->
            acc += c
            Direction.lookup[acc]?.also { direction ->
                directions.add(direction)
                acc = ""
            }
        }
        directions
    }.toList()

    fun flip(instructions: List<List<Direction>>): Set<Tile> {
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

    enum class Direction(val s: String) {
        E("e"),
        SE("se"),
        SW("sw"),
        W("w"),
        NW("nw"),
        NE("ne");

        companion object {
            val lookup = values().map { direction -> direction.s to direction }.toMap()
        }
    }

    data class Tile(val x: Int, val y: Int, val z: Int) {
        fun next(direction: Direction): Tile = when (direction) {
            Direction.E -> copy(x = x + 1, y = y - 1)
            Direction.SE -> copy(y = y - 1, z = z + 1)
            Direction.SW -> copy(x = x - 1, z = z + 1)
            Direction.W -> copy(x = x - 1, y = y + 1)
            Direction.NW -> copy(y = y + 1, z = z - 1)
            Direction.NE -> copy(x = x + 1, z = z - 1)
        }

        fun neighbors(): Set<Tile> = Direction.values().map { next(it) }.toSet()
    }
}