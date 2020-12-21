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
        val allergenIngredients = map.values.toSet()
        val withoutAllergens = foods.map { (it - allergenIngredients).size }.sum()
        assertEquals(5, withoutAllergens)
    }

    @Test
    fun isolate() {
        val (_, allergens) = Day21.parse(input)
        val answer = allergens.map { it.key to it.value }.sortedBy { it.first }.joinToString(",") { it.second }
        assertEquals("mxmxvkd,sqjhc,fvjkl", answer)
    }
}