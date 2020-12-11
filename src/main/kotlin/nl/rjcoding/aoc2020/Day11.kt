package nl.rjcoding.aoc2020

object Day11 : Day {

    val input= Util.readInputToLines("day11.txt").let(::parse)

    override fun part1(): Long = answer(updates(input, 1))

    override fun part2(): Long = answer(updates(input, 2))

    fun parse(input: Sequence<String>): Seating = input.toList().let { Seating(it.size, it.first().length, it.joinToString("").toCharArray()) }

    fun answer(seatings: Sequence<Seating>) = seatings
        .map { it to it.seatsOccupied() }
        .windowed(2)
        .takeWhile { (prev, next) -> prev.second != next.second }
        .last().last().second.toLong()

    fun updates(seating: Seating, part: Int) = when (part) {
        1 -> generateSequence(seating) { it.update( { r, c -> it.adjecentsByOffsets(r, c) }, 4 ) }
        2 -> generateSequence(seating) { it.update( { r, c -> it.adjecentsByRays(r, c) }, 5 ) }
        else -> emptySequence()
    }

    class Seating(val rows: Int, val cols: Int, private val data: CharArray) {

        val seats: List<Pair<Int, Int>> = (0 until rows).flatMap { r -> (0 until cols).map { c -> r to c } }.filter { (r, c) -> this[r, c] != '.' }

        fun copy(): Seating = Seating(rows, cols, data.clone())

        operator fun get(r: Int, c: Int): Char = data[r * cols + c]
        operator fun set(r: Int, c: Int, v: Char) { data[r * cols + c] = v }

        fun contains(r: Int, c: Int): Boolean = when {
            r < 0 || r >= rows -> false
            c < 0 || c >= cols -> false
            else -> true
        }

        fun seatsOccupied(): Int = seats.count { (r, c) -> isOccupied(r, c) }

        fun adjecentsByOffsets(r: Int, c: Int): List<Pair<Int, Int>> = offsets.map { (i, j) -> (r + i) to (c + j) }

        fun adjecentsByRays(r: Int, c: Int) = rays.map { ray ->
            ray.map { (i, j) -> (r + i) to (c + j) }
                .first { (i, j) -> !contains(i, j) || this[i, j] != '.' }
        }

        fun isOccupied(r: Int, c: Int) = when {
            !contains(r, c) -> false
            else -> this[r, c] == '#'
        }

        fun update(adjecents: (Int, Int) -> List<Pair<Int, Int>>, limit: Int): Seating = copy().also { copy ->
            seats.forEach { (r, c) ->
                when {
                    this[r, c] == 'L' && adjecents(r, c).all { (i, j) -> !isOccupied(i, j) } -> copy[r, c] = '#'
                    this[r, c] == '#' && adjecents(r, c).filter { (i, j) -> isOccupied(i, j) }.size >= limit -> copy[r, c] = 'L'
                }
            }
        }
    }
}