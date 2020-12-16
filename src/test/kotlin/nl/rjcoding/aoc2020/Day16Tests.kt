package nl.rjcoding.aoc2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Tests {

    val input = """
        class: 1-3 or 5-7
        row: 6-11 or 33-44
        seat: 13-40 or 45-50

        your ticket:
        7,1,14

        nearby tickets:
        7,3,47
        40,4,50
        55,2,20
        38,6,12
    """.trimIndent().lineSequence()

    val input2 = """
        class: 0-1 or 4-19
        row: 0-5 or 8-19
        seat: 0-13 or 16-19
        
        your ticket:
        11,12,13
        
        nearby tickets:
        3,9,18
        15,1,5
        5,14,9
    """.trimIndent().lineSequence()

    @Test
    fun validate() {
        val (fields, _, others) = Day16.parse(input)
        val results = Day16.errorRate(others, fields)
        assertEquals(71, results)
    }

    @Test
    fun fieldOrder() {
        val (fields, _, others) = Day16.parse(input2)
        val valids = others.filter { Day16.validate(it, fields).isEmpty() }
        val orderedFields = Day16.fieldOrder(fields, valids)
    }

}