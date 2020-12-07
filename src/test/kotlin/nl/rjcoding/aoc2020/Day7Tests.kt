package nl.rjcoding.aoc2020

import nl.rjcoding.aoc2020.Day7.lookupTable
import nl.rjcoding.aoc2020.Day7.parse
import org.junit.Test
import kotlin.test.assertEquals

class Day7Tests {

    val input = """
        light red bags contain 1 bright white bag, 2 muted yellow bags.
        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        bright white bags contain 1 shiny gold bag.
        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        faded blue bags contain no other bags.
        dotted black bags contain no other bags.
    """.trimIndent()

    val input2 = """
        shiny gold bags contain 2 dark red bags.
        dark red bags contain 2 dark orange bags.
        dark orange bags contain 2 dark yellow bags.
        dark yellow bags contain 2 dark green bags.
        dark green bags contain 2 dark blue bags.
        dark blue bags contain 2 dark violet bags.
        dark violet bags contain no other bags.
    """.trimIndent()

    @Test
    fun findBagsContaining() {
        val lookup = input.lineSequence().map(::parse).let(::lookupTable)
        val bags = Day7.findBagsContaining("shiny gold", lookup)
        assertEquals(4, bags.size)
    }

    @Test
    fun contentCount() {
        val lookup = input.lineSequence().map(::parse).let(::lookupTable)
        val count = Day7.contentCount("shiny gold", lookup)
        assertEquals(32, count)
    }

    @Test
    fun contentCount2() {
        val lookup = input2.lineSequence().map(::parse).let(::lookupTable)
        val count = Day7.contentCount("shiny gold", lookup)
        assertEquals(126, count)
    }
}