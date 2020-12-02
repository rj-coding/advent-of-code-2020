package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Day2.evaluateNewPasswordPolicy
import nl.rjcoding.aoc2020.Day2.evaluateOldPasswordPolicy
import org.junit.Test
import kotlin.test.assertEquals

class Day2Tests {

    @Test
    fun testEvaluateOldPasswordPolicy() {
        val inputs = listOf(
            "1-3 a" to "abcde",
            "1-3 a" to "abcdeaaa",
            "1-3 b" to "cdefg",
            "2-9 c" to "ccccccccc",
            "2-9 c" to "cccccccccc"
        )

        val outputs = listOf(true, false, false, true, false)

        inputs.zip(outputs).forEach { (input, output) ->
            val (rule, password) = input
            val result = evaluateOldPasswordPolicy(rule, password)
            assertEquals(output, result)
        }
    }

    @Test
    fun testEvaluateNewPasswordPolicy() {
        val inputs = listOf(
            "1-3 a" to "abcde",
            "1-3 a" to "abcdeaaa",
            "1-3 b" to "cdefg",
            "2-9 c" to "ccccccccc",
            "2-9 c" to "cccccccccc"
        )

        val outputs = listOf(true, true, false, false, false)

        inputs.zip(outputs).forEach { (input, output) ->
            val (rule, password) = input
            val result = evaluateNewPasswordPolicy(rule, password)
            assertEquals(output, result, message = "$rule on $password failed")
        }
    }
}