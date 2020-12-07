package nl.rjcoding.aoc2020

object Day7 : Day {
    override fun part1(): Long = Util.readInputToLines("day7.txt")
        .map(::parse)
        .let(::lookupTable)
        .let { table ->
            findBagsContaining("shiny gold", table)
        }.size.toLong()

    override fun part2(): Long {
        TODO("Not yet implemented")
    }

    fun parse(line: String): Rule = line.split("contain").let { (bag, contents) ->
        val bagColor = color(bag)
        val contents = contents.split(", ").mapNotNull(::amountAndColor)
        Rule(bagColor, contents)
    }

    fun lookupTable(rules: Sequence<Rule>): Map<String, Rule> = rules.fold(mapOf()) { map, rule ->
        map + (rule.bagColor to rule)
    }

    fun findBagsContaining(color: String, lookup: Map<String, Rule>): List<Rule> {
        return lookup.values.filter { rule -> containsColor(rule, color, lookup) }
    }

    fun containsColor(rule: Rule, color: String, lookup: Map<String, Rule>): Boolean {
        return rule.contents.any { it.second == color || containsColor(lookup[it.second]!!, color, lookup) }
    }

    fun amountAndColor(str: String): Pair<Long, String>? = str.trim().split(" ").let { parts ->
        when (parts.size) {
            4 -> parts[0].toLong() to parts[1] + " " + parts[2]
            else -> null
        }
    }

    fun color(str: String): String = str.split(" ").take(2).joinToString(" ")

    data class Rule(val bagColor: String, val contents: List<Pair<Long, String>>)

}