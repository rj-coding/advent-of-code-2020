package nl.rjcoding.aoc2020

import java.util.*

typealias Op = (Long, Long) -> Long

object Day18 : Day {
    val PLUS : Op = { l, r -> l + r }
    val TIMES : Op = { l, r -> l * r }

    override fun part1(): Long = Util.readInputToLines("day18.txt").map { eval(it, false) }.sum()

    override fun part2(): Long = Util.readInputToLines("day18.txt").map { eval(it, true) }.sum()

    fun eval(input: String, precedence: Boolean): Long {
        val values = Stack<Long>().also { it.add(1) }
        val ops = Stack<Op>().also { it.add(TIMES) }

        fun apply() { values.add(ops.pop().invoke(values.pop(), values.pop()))}

        input.forEach { c ->
            when {
                c.isDigit() -> c.toString().toLong().also { d ->
                    values.add(d)
                    if (!precedence && ops.isNotEmpty()) apply()
                    if (precedence && ops.isNotEmpty() && ops.peek() == PLUS) apply()
                }
                c == '+' -> ops.add(PLUS)
                c == '*' -> {
                    if (precedence && ops.isNotEmpty() && ops.peek() == TIMES) apply()
                    ops.add(TIMES)
                }
                c == '(' -> {
                    values.add(1L)
                    ops.add(TIMES)
                }
                c == ')' -> apply()
            }
        }

        while (ops.isNotEmpty()) {
            values.add(ops.pop().invoke(values.pop(), values.pop()))
        }
        return values.pop()
    }
}