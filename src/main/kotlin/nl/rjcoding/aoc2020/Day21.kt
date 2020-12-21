package nl.rjcoding.aoc2020

import java.util.*

object Day21 : GenericDay<Long, String> {

    val REGEX = Regex("(.*|\\s*) \\(contains (.*)\\)")

    override fun part1(): Long = Util.readInputToLines("day21.txt").let(::parse).let { (foods, map) ->
        map.flatMap { it.value }.toSet().let { allergenIngredients ->
            foods.map { (it - allergenIngredients).size }.sum().toLong()
        }
    }

    override fun part2(): String = Util.readInputToLines("day21.txt").let(::parse).second.let(::isolate).let { allergens ->
        allergens.map { it.key to it.value }.sortedBy { it.first }.joinToString(",") { it.second }
    }

    fun parse(input: Sequence<String>): Pair<List<Set<String>>,Map<String, Set<String>>> {
        val foods = mutableListOf<Set<String>>()
        val map = mutableMapOf<String, Set<String>>()
        input.forEach { line ->
            REGEX.matchEntire(line)!!.groupValues.also { (_, ingredientsLine, allergensLine) ->
                val ingredients = ingredientsLine.split(" ").toSet().also { foods.add(it) }
                val allergens = allergensLine.split(", ")
                allergens.forEach { allergen ->
                    map[allergen] = map.getOrDefault(allergen, ingredients).intersect(ingredients)
                }
            }
        }
        return foods to map
    }

    fun isolate(map: Map<String, Set<String>>): Map<String, String> {
        val result = mutableMapOf<String, String>()
        val toEliminate = Stack<String>()
        val remaining = mutableMapOf<String, Set<String>>()

        map.entries.forEach { (allergen, ingredients) ->
            if (ingredients.size == 1) {
                result[allergen] = ingredients.first()
                toEliminate.add(ingredients.first())
            } else {
                remaining[allergen] = ingredients
            }
        }

        while (toEliminate.isNotEmpty()) {
            val ingredient = toEliminate.pop()
            val toRemove = mutableSetOf<String>()
            remaining.keys.forEach { allergen ->
                remaining[allergen] = remaining[allergen]!!.minus(ingredient)
                if (remaining[allergen]!!.size == 1) {
                    result[allergen] = remaining[allergen]!!.first()
                    toEliminate.add(remaining[allergen]!!.first())
                    toRemove.add(allergen)
                }
            }
            toRemove.forEach { remaining.remove(it) }
        }
        return result
    }
}