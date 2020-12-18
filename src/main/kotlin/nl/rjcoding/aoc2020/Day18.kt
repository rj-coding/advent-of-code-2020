package nl.rjcoding.aoc2020

import java.util.*

typealias Op = (Long, Long) -> Long

object Day18 : Day {
    val PLUS : Op = { l, r -> l + r }
    val TIMES : Op = { l, r -> l * r }

    override fun part1(): Long = Util.readInputToLines("day18.txt").map { eval(it, false) }.sum()

    override fun part2(): Long = Util.readInputToLines("day18.txt").map { eval(it, true) }.sum()

    fun eval(input: String, precedence: Boolean): Long {

        val simplified = parentheses(input).fold(input) { acc, group ->
            acc.replace("($group)", eval(group, precedence).toString())
        }

        val values = Stack<Long>().also { it.add(1) }
        val ops = Stack<Op>().also { it.add(TIMES) }

        fun apply() { values.add(ops.pop().invoke(values.pop(), values.pop()))}

        simplified.split(" ").forEach { c ->
            val d = c.toLongOrNull()
            when {
                d != null -> {
                    values.add(d)
                    if (!precedence && ops.isNotEmpty()) apply()
                    if (precedence && ops.isNotEmpty() && ops.peek() == PLUS) apply()
                }

                c == "+" -> ops.add(PLUS)
                c == "*" -> {
                    if (precedence && ops.isNotEmpty() && ops.peek() == TIMES) apply()
                    ops.add(TIMES)
                }
            }
        }

        while (ops.isNotEmpty()) {
            values.add(ops.pop().invoke(values.pop(), values.pop()))
        }
        return values.pop()
    }

    fun parentheses(input: String): Set<String> {
        val groups = mutableSetOf<String>()
        var level = 0
        val acc = mutableListOf<Char>()
        input.forEach { char ->
            when  {
                char == '(' -> {
                    level++
                    if (level > 1) {
                        acc.add(char)
                    }
                }
                char  == ')' -> {
                    level--
                    if (level == 0) {
                        groups.add(acc.joinToString("").trim())
                        acc.clear()
                    } else {
                        acc.add(char)
                    }
                }
                level > 0 -> acc.add(char)
            }
        }
        return groups
    }
}