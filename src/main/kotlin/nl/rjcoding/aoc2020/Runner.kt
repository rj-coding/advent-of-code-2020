package nl.rjcoding.aoc2020

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun stats(runtimes: List<Long>): Pair<Double, Double> {
    val mean = runtimes.sum().toDouble() / runtimes.size
    val stdev = sqrt(runtimes.sumOf { x -> (x - mean) * (x - mean) } / runtimes.size)
    return mean to stdev
}

fun main() {
    val allDays = listOf(Day1, Day2, Day3, Day4, Day5, Day6, Day7, Day8, Day9, Day10, Day11, Day12, Day13, Day14)
    val runs = 20
    val warmup = 5

    allDays.forEach { day ->
        val name = day::class.simpleName ?: ""
        println(name)
        runPart("\tPart 1", warmup, runs) { day.part1() }
        runPart("\tPart 2", warmup, runs) { day.part2() }
    }
}

fun runPart(label: String, warmup: Int, runs: Int, runner: () -> Unit) {
    val runtimes = mutableListOf<Long>()
    print(label)

    repeat(warmup) { runner() }
    repeat(runs) {
        runtimes += measureTimeMillis { runner()}
        print(".")
    }
    stats(runtimes).also { (mean, stdev) ->
        println("m=%3.3f ms (s=%3.3f ms)".format(mean,stdev))
    }
}

