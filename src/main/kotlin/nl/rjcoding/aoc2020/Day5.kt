package nl.rjcoding.aoc2020

object Day5  : Day {

    override fun part1(): Long = Util.readInputToLines("day5.txt")
        .map(::parse)
        .map { it.id() }
        .maxByOrNull { it }!!.toLong()

    override fun part2(): Long = Util.readInputToLines("day5.txt")
        .map(::parse)
        .groupBy { it.row.value() }
        .mapNotNull { (row, seats) ->
            val usedColumns = seats.map { it.col.value() }
            val (min, max) = usedColumns.minByOrNull { it }!! to usedColumns.maxByOrNull { it }!!
            (min .. max).toSet().minus(usedColumns).map { row * 8 + it }.firstOrNull()
        }.first().toLong()

    fun parse(code: String): Seat = code
        .asSequence()
        .fold(Seat.init) { seat, char ->
            when (char) {
                'F' -> seat.front()
                'B' -> seat.back()
                'L' -> seat.left()
                'R' -> seat.right()
                else -> seat
            }
        }

    data class Seat(val row: Region, val col: Region) {
        fun front(): Seat = copy(row = row.lower())
        fun back(): Seat = copy(row = row.upper())
        fun left(): Seat = copy(col = col.lower())
        fun right(): Seat = copy(col = col.upper())

        fun id(): Int = row.value() * 8 + col.value()

        companion object {
            val init = Seat(Region.row, Region.col)
        }
    }

    data class Region(val min: Int, val max: Int) {
        fun lower(): Region = copy(max = min + (max - min) / 2)
        fun upper(): Region = copy(min = min + (max - min + 1) / 2)

        fun value(): Int {
            require(min == max) { "Value not fully determined" }
            return min
        }

        companion object {
            val row = Region(0, 127)
            val col = Region(0, 7)
        }
    }
}