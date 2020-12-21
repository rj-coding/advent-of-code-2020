package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Tests {

    val input = """
        mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
        trh fvjkl sbzzf mxmxvkd (contains dairy)
        sqjhc fvjkl (contains soy)
        sqjhc mxmxvkd sbzzf (contains fish)
    """.trimIndent().lineSequence()

    @Test
    fun parse() {
        val (foods, map) = Day21.parse(input)
        val allergenIngredients = map.flatMap { it.value }.toSet()
        val withoutAllergens = foods.map { (it - allergenIngredients).size }.sum()
        assertEquals(5, withoutAllergens)
    }

    @Test
    fun isolate() {
        val (_, map) = Day21.parse(input)
        val allergens = Day21.isolate(map)
        val answer = allergens.map { it.key to it.value }.sortedBy { it.first }.joinToString(",") { it.second }
        assertEquals("mxmxvkd,sqjhc,fvjkl", answer)
    }
}