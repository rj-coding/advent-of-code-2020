package nl.rjcoding.aoc2020

object Day17 : Day {

    override fun part1(): Long = input.reduceRepeated(6) { step(it, 1) }.size.toLong()
    override fun part2(): Long = input.reduceRepeated(6) { step(it, 2) }.size.toLong()

    val input = Util.readInputToLines("day17.txt").let(::parse)

    val neighborOffsets = (-1 until 2).flatMap { dx ->
        (-1 until 2).flatMap { dy ->
            (-1 until 2).flatMap { dz ->
                (-1 until 2).map { dw ->
                    HPoint(dx, dy, dz, dw)
                }
            }
        }
    }.filter { (dx, dy, dz, dw ) -> !(dx == 0 && dy == 0 && dz ==0 && dw == 0) }

    fun parse(input: Sequence<String>): Set<HPoint> = input.foldIndexed(setOf()) { y, set, line ->
        set + line.mapIndexedNotNull { x, c ->
            when (c) {
                '#' -> HPoint(x, y, 0, 0)
                else -> null
            }
        }
    }

    fun neighborsOf(cube: HPoint, part: Int) = neighborOffsets
        .filter { part != 1 || it.w == 0 }
        .map { (dx, dy, dz, dw) ->
        HPoint(cube.x + dx, cube.y + dy, cube.z + dz, cube.w + dw)
    }

    fun step(cubes: Set<HPoint>, part: Int): Set<HPoint> {
        val boundary = mutableSetOf<HPoint>()
        cubes.flatMap { cube -> neighborsOf(cube, part) }.forEach { n ->
            if (!cubes.contains(n)) boundary.add(n)
        }

        val next = mutableSetOf<HPoint>()
        cubes.forEach { cube ->
            if (neighborsOf(cube, part).count { n -> cubes.contains(n) } in 2..3) {
                next.add(cube)
            }
        }

        boundary.forEach { inactive ->
            if (neighborsOf(inactive, part).count { n -> cubes.contains(n) } == 3) {
                next.add(inactive)
            }
        }
        return next
    }

    data class HPoint(val x: Int, val y: Int, val z: Int, val w: Int)
}