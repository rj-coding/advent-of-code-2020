package nl.rjcoding.aoc2020

object Day17 : Day {

    val input = Util.readInputToLines("day17.txt").toList()
    private val neighborOffsets = mutableMapOf<Int, List<List<Int>>>()

    override fun part1(): Long = parse(input, 3).reduceRepeated(6) { step(it) }.size.toLong()
    override fun part2(): Long = parse(input, 4).reduceRepeated(6) { step(it) }.size.toLong()

    fun parse(lines: List<String>, dims: Int): Set<List<Int>> = lines.foldIndexed(setOf()) { y, set, line ->
        set + line.mapIndexedNotNull { x, c ->
            when (c) {
                '#' -> listOf(x, y) + List(dims - 2) { 0 }
                else -> null
            }
        }
    }

    fun neighborOffsets(dim: Int) = neighborOffsets.getOrPut(dim) { listOf(-1, 0, 1).combinations(dim).filter { c -> !c.all { it == 0 } } }

    fun neighborsOf(point: List<Int>): List<List<Int>> = neighborOffsets(point.size)
        .map { neighbor -> point.zip(neighbor).map { (i, di) -> i + di } }

    fun step(cubes: Set<List<Int>>): Set<List<Int>> {
        val boundary = mutableSetOf<List<Int>>()
        cubes.flatMap { point -> neighborsOf(point) }.forEach { n ->
            if (!cubes.contains(n)) boundary.add(n)
        }

        val next = mutableSetOf<List<Int>>()
        cubes.forEach { cube ->
            if (neighborsOf(cube).count { n -> cubes.contains(n) } in 2..3) {
                next.add(cube)
            }
        }

        boundary.forEach { inactive ->
            if (neighborsOf(inactive).count { n -> cubes.contains(n) } == 3) {
                next.add(inactive)
            }
        }
        return next
    }
}