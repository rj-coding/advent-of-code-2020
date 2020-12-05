package nl.rjcoding.aoc2020

object Day5  : Day {

    val allSeats = (0..1023).toSet()

    override fun part1(): Long = Util.readInputToLines("day5.txt")
        .map(::parse)
        .maxByOrNull { it }!!.toLong()

    override fun part2(): Long = Util.readInputToLines("day5.txt")
        .map(::parse).toSet()
        .let { occupied -> occupied to allSeats - occupied }
        .let { (occupied, remaining) ->
            remaining.first { seatId -> occupied.contains(seatId - 1) && occupied.contains(seatId + 1) }
        }.toLong()

    fun parse(code: String): Int = code
        .map { char ->
            when (char) {
                'F','L' -> '0'
                'B','R' -> '1'
                else -> '0'
            }
        }.joinToString("").toInt(2)
}