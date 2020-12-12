package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Complex.Companion.UNIT
import nl.rjcoding.aoc2020.Complex.Companion.ZERO
import nl.rjcoding.aoc2020.Constants.j
import nl.rjcoding.aoc2020.Day12.State.Companion.ShipMode
import nl.rjcoding.aoc2020.Day12.State.Companion.WaypointMode
import kotlin.math.abs

object Day12 : Day {

    private val PATTERN = Regex("([A-Z])(\\d+)")
    private val commands =  Util.readInputToLines("day12.txt").let(::parse)

    override fun part1(): Long = navigate(commands, State(ZERO, UNIT, ShipMode)).distance
    override fun part2(): Long = navigate(commands, State(ZERO, 10 + j, WaypointMode)).distance

    fun parse(lines: Sequence<String>): List<Pair<String, Int>> = Util.readInputRegex(lines, PATTERN)
        .map { (_, command, data) -> command to data.toInt() }
        .toList()

    fun navigate(commands: List<Pair<String, Int>>, init: State): State = commands.fold(init) { state, (command, data) ->
        state.navigate(command, data)
    }

    data class State(val pos: Complex, val waypoint: Complex, val updater: (State, Complex) -> State) {

        val distance = abs(pos.i) + abs(pos.j)

        fun navigate(command: String, data: Int): State = when (command) {
            "N" -> updater(this, Complex(0, data))
            "E" -> updater(this, Complex(data, 0))
            "S" -> updater(this, Complex(0, -data))
            "W" -> updater(this, Complex(-data, 0))
            "L" -> copy(waypoint = waypoint.letRepeated(data / 90) { it * j})
            "R" -> copy(waypoint = waypoint.letRepeated(data / 90) { it * -j})
            "F" -> copy(pos = pos + waypoint * data)
            else -> this
        }

        companion object {
            val ShipMode: (State, Complex) -> State = { state, offset -> state.copy(pos = state.pos + offset) }
            val WaypointMode: (State, Complex) -> State = { state, offset -> state.copy(waypoint = state.waypoint + offset) }
        }
    }
}