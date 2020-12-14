package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Day14.Command.Companion.fromString

object Day14 : Day {

    val input = Util.readInputToLines("day14.txt").map(::fromString).toList()

    override fun part1(): Long = input
        .fold(State.INIT) { state, command -> state.update(command, 1) }
        .memory.values.sum()

    override fun part2(): Long = input
        .fold(State.INIT) { state, command -> state.update(command, 2) }
        .memory.values.sum()

    fun parse(input: Sequence<String>) = input.map { Command.fromString(it) }

    data class State(val mask: Command.Mask, val memory: Map<Long, Long>) {
        fun update(command: Command, version: Int): State = when (command) {
            is Command.Mask -> copy(mask = command)
            is Command.Write -> when (version) {
                1 -> copy(memory = memory + (command.address to mask(command.value)))
                2 -> copy(memory = memory + mask.address(command.address).addresses.map { it to command.value })
                else -> this
            }
        }

        companion object {
            val INIT = State(Command.Mask.INIT, mapOf())
        }
    }

    sealed class Command {
        companion object {
            fun fromString(input: String): Command = when {
                Mask.REGEX.matches(input) -> Mask.fromString(input)
                Write.REGEX.matches(input) -> Write.fromString(input)
                else -> error("Invalid input")
            }
        }

        data class Mask(val mask: String) : Command() {

            val clear = mask.map { if (it == 'X') '1' else '0' }.joinToString("").toLong(2)
            val set = mask.map { if (it == 'X') '0' else it }.joinToString("").toLong(2)

            val addresses: List<Long> by lazy {
                mask.fold(listOf(0)) { list, b ->
                    when (b) {
                        'X' -> list.flatMap { listOf(it * 2, it * 2 + 1) }
                        else -> list.map { it * 2 + b.toString().toLong() }
                    }
                }
            }

            fun address(input: Long) = invoke(input.toString(2).padStart(36, '0'))

            operator fun invoke(input: Long): Long = input and clear or set

            operator fun invoke(input: String): Mask = input.zip(mask).map { (i, m) ->
                when (m) {
                    '1', 'X' -> m
                    else -> i
                }
            }.joinToString("").let { Mask(it) }

            companion object {
                val REGEX = Regex("mask = ([X,1,0]{36})")
                val INIT = fromString("mask = " + "X".repeat(36))

                fun fromString(input: String): Mask = REGEX.matchEntire(input)!!.groupValues.let { (_, mask) -> Mask(mask) }
            }
        }

        data class Write(val address: Long, val value: Long): Command() {
            companion object {
                val REGEX = Regex("mem\\[(\\d*)\\] = (\\d*)")

                fun fromString(input: String): Write = REGEX.matchEntire(input)!!.groupValues.let { (_, index, value) ->
                    Write(index.toLong(), value.toLong())
                }
            }
        }
    }
}