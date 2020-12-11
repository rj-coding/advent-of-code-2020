package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Util.offsets
import nl.rjcoding.aoc2020.Util.rays

object Day11 : Day {

    val input= Util.readInputToLines("day11.txt").let(::parse)

    override fun part1(): Long = answer(updates(input, 1, 4))

    override fun part2(): Long = answer(updates(input, 2, 5))

    fun parse(input: Sequence<String>): Seating = input.toList().let { Seating(it.size, it.first().length, it.joinToString("").toCharArray()) }

    fun answer(seatings: Sequence<Seating>) = seatings
        .map { it to it.seatsOccupied() }
        .windowed(2)
        .takeWhile { (prev, next) -> prev.second != next.second }
        .last().last().second.toLong()

    fun updates(seating: Seating, part: Int, limit: Int) = generateSequence(seating) { it.update(part, limit ) }

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

        fun occupiedByOffsets(r: Int, c: Int): List<Boolean> = offsets.map { (i, j) -> isOccupied(r + i, c + j) }

        fun occupiedByRays(r: Int, c: Int) = rays.map { ray ->
            ray.map { (i, j) -> (r + i) to (c + j) }
                .first { (i, j) -> !contains(i, j) || this[i, j] != '.' }
                .let { (i, j) -> isOccupied(i, j) }
        }

        fun isOccupied(r: Int, c: Int) = when {
            !contains(r, c) -> false
            else -> this[r, c] == '#'
        }

        fun update(mode: Int, limit: Int): Seating = copy().also { copy ->
            seats.forEach { (r, c) ->
                val neighbours = when (mode) {
                    1 -> occupiedByOffsets(r, c)
                    2 -> occupiedByRays(r, c)
                    else -> emptyList()
                }
                when {
                    this[r, c] == 'L' && neighbours.all { !it } -> copy[r, c] = '#'
                    this[r, c] == '#' && neighbours.filter { it }.size >= limit -> copy[r, c] = 'L'
                }
            }
        }
    }
}