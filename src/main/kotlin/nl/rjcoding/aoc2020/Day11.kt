package nl.rjcoding.aoc2020

object Day11 : Day {

    val input get() = Util.readInputToLines("day11.txt").let(::parse)

    override fun part1(): Long = answer(updates(input, 1))

    override fun part2(): Long = answer(updates(input, 2))

    fun parse(input: Sequence<String>): Map = Map(input.map { it.toCharArray() }.toList().toTypedArray())

    fun answer(maps: Sequence<Map>) = maps
        .map { it to it.seatsOccupied() }
        .windowed(2)
        .takeWhile { (prev, next) -> prev.second != next.second }
        .last().last().first.seatsOccupied().toLong()

    fun updates(map: Map, part: Int) = when (part) {
        1 -> generateSequence(map) { it.update( { r, c -> it.adjecentsByOffsets(r, c) }, 4 ) }
        2 -> generateSequence(map) { it.update( { r, c -> it.adjecentsByRays(r, c) }, 5 ) }
        else -> emptySequence()
    }

    class Map(private val seating: Array<CharArray>) {
        val rows = seating.size
        val cols = seating.first().size

        fun copy(): Map = Map(seating.map { it.clone() }.toTypedArray())

        operator fun get(r: Int, c: Int): Char = seating[r][c]
        operator fun set(r: Int, c: Int, v: Char) { seating[r][c] = v }

        fun withinBounds(r: Int, c: Int): Boolean = when {
            r < 0 || r >= rows -> false
            c < 0 || c >= cols -> false
            else -> true
        }

        fun seats(): Sequence<Pair<Int, Int>> = (0 until rows).asSequence().flatMap { r -> (0 until cols).asSequence().map { c -> r to c } }
        fun seatsOccupied(): Int = seats().count { (r, c) -> isOccupied(r, c) }

        fun adjecentsByOffsets(r: Int, c: Int): List<Pair<Int, Int>> = offsets().map { (i, j) -> (r + i) to (c + j) }

        fun adjecentsByRays(r: Int, c: Int) = rays().map { ray ->
            ray.map { (i, j) -> (r + i) to (c + j) }
                .first { (i, j) -> !withinBounds(i, j) || this[i, j] != '.' }
        }

        fun isOccupied(r: Int, c: Int) = when {
            !withinBounds(r, c) -> false
            else -> seating[r][c] == '#'
        }

        fun update(adjecents: (Int, Int) -> List<Pair<Int, Int>>, limit: Int): Map = copy().also { copy ->
            seats().forEach { (r, c) ->
                when {
                    this[r, c] == 'L' && adjecents(r, c).all { (i, j) -> !isOccupied(i, j) } -> copy[r, c] = '#'
                    this[r, c] == '#' && adjecents(r, c).filter { (i, j) -> isOccupied(i, j) }.size >= limit -> copy[r, c] = 'L'
                }
            }
        }
    }
}