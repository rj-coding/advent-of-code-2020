package nl.rjcoding.aoc2020

object Day2 : Day {
    override fun part1(): Long {
        return Util.readInputToLines("day2.txt")
            .map { str -> str.split(":") }
            .map { parts -> parts[0].trim() to parts[1].trim() }
            .count { (rule, password) ->
                evaluateOldPasswordPolicy(rule, password)
            }.toLong()
    }

    override fun part2(): Long {
        return Util.readInputToLines("day2.txt")
            .map { str -> str.split(":") }
            .map { parts -> parts[0].trim() to parts[1].trim() }
            .count { (rule, password) ->
                evaluateNewPasswordPolicy(rule, password)
            }.toLong()
    }

    fun evaluateOldPasswordPolicy(rule: String, password: String): Boolean {
        val parts = rule.split(' ')
        val character = parts[1].single()
        val (min, max) = parts[0].split('-').let { it[0].toInt() to it[1].toInt() }
        val counts = password.count { it == character }
        return counts in min..max
    }

    fun evaluateNewPasswordPolicy(rule: String, password: String): Boolean {
        val parts = rule.split(' ')
        val character = parts[1].single()
        val (first, second) = parts[0].split('-').let { it[0].toInt() - 1 to it[1].toInt() - 1 }
        return (password[first] == character).xor(password[second] == character)
    }
}