package nl.rjcoding.aoc2020

import kotlin.math.abs

typealias Commands = List<Pair<String, Int>>

object Day12 : Day {

    private val PATTERN = Regex("([A-Z])(\\d+)")
    private val commands =  Util.readInputToLines("day12.txt").let(::parse)

    override fun part1(): Long = navigate(commands, State(0, 0, 1, 0, State.ShipMode)).distance
    override fun part2(): Long = navigate(commands, State(0, 0, 10, 1, State.WaypointMode)).distance

    fun parse(lines: Sequence<String>): Commands = Util.readInputRegex(lines, PATTERN)
        .map { (_, command, data) -> command to data.toInt() }
        .toList()

    fun navigate(commands: Commands, init: State): State = commands.fold(init) { state, (command, data) ->
        state.navigate(command, data)
    }

    data class State(val pos: Complex, val waypoint: Complex, val updater: (State, Complex) -> State) {

        constructor(x: Long, y: Long, i: Long, j: Long, updater: (State, Complex) -> State) : this(Complex(x, y), Complex(i, j), updater)

        val distance = abs(pos.i) + abs(pos.j)

        fun navigate(command: String, data: Int): State = when (command) {
            "N" -> updater(this, Complex(0, data))
            "E" -> updater(this, Complex(data, 0))
            "S" -> updater(this, Complex(0, -data))
            "W" -> updater(this, Complex(-data, 0))
            "L" -> copy(waypoint = waypoint.letRepeated(data / 90) { it * (0L to 1L)})
            "R" -> copy(waypoint = waypoint.letRepeated(data / 90) { it * (0L to -1L)})
            "F" -> copy(pos = pos + waypoint * data)
            else -> this
        }

        companion object {
            val ShipMode: (State, Complex) -> State = { state, offset -> state.copy(pos = state.pos + offset) }
            val WaypointMode: (State, Complex) -> State = { state, offset -> state.copy(waypoint = state.waypoint + offset) }
        }
    }
}