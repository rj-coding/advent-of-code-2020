package nl.rjcoding.aoc2020

import kotlin.math.abs

typealias Commands = List<Pair<String, Int>>

object Day12 : Day {

    private val PATTERN = Regex("([A-Z])(\\d+)")
    private val commands =  Util.readInputToLines("day12.txt").let(::parse)

    override fun part1(): Long = navigate(commands, State(0, 0, 1, 0), mode = 0).distance
    override fun part2(): Long = navigate(commands, State(0, 0, 10, 1), mode = 1).distance

    fun parse(lines: Sequence<String>): Commands = Util.readInputRegex(lines, PATTERN)
        .map { (_, command, data) -> command to data.toInt() }
        .toList()

    fun navigate(commands: Commands, init: State, mode: Int): State = commands.fold(init) { state, (command, data) ->
        state.navigate(command, data, mode)
    }

    data class State(val pos: Complex, val waypoint: Complex) {

        constructor(x: Long, y: Long, i: Long, j: Long) : this(Complex(x, y), Complex(i, j))

        val distance = abs(pos.i) + abs(pos.j)

        fun navigate(command: String, data: Int, mode: Int): State = when (command) {
            "N" -> copy(pos = pos + Complex(0, data) * (1 - mode), waypoint = waypoint + Complex(0, data) * mode)
            "E" -> copy(pos = pos + Complex(data, 0) * (1 - mode), waypoint = waypoint + Complex(data, 0) * mode)
            "S" -> copy(pos = pos - Complex(0, data) * (1 - mode), waypoint = waypoint - Complex(0, data) * mode)
            "W" -> copy(pos = pos - Complex(data, 0) * (1 - mode), waypoint = waypoint - Complex(data, 0) * mode)
            "L" -> copy(waypoint = (0 until data / 90).fold(waypoint) { acc, _ -> acc * (0L to 1L) } )
            "R" -> copy(waypoint = (0 until data / 90).fold(waypoint) { acc, _ -> acc * (0L to -1L) } )
            "F" -> copy(pos = pos + waypoint * data)
            else -> this
        }
    }
}