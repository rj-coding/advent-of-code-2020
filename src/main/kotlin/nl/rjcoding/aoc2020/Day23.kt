package nl.rjcoding.aoc2020

object Day23 : Day {

    override fun part1(): Long = 459672813L.toDigits().let {
        play(it.first(), parse(it), 100)
    }.let(::order).toLong()

    override fun part2(): Long = 459672813L.toDigits().let {
        val cups = it + (10 .. 1_000_000)
        play(cups.first(), parse(cups), 10_000_000)
    }.let { result ->
        val a = result[1]!!
        val b = result[a]!!
        a.toLong() * b.toLong()
    }

    fun parse(input: List<Int>): HashMap<Int, Int> {
        val size = input.size
        return hashMapOf<Int, Int>().also {
            input.indices.forEach { i ->
                it[input[i]] = input[(i + 1) % size]
            }
        }
    }

    fun play(start: Int, cups: HashMap<Int, Int>, amount: Int): HashMap<Int, Int> {
        val result = (start to cups).reduceRepeated(amount) { acc -> round(acc.first, acc.second) }
        return result.second
    }

    fun order(cups: HashMap<Int, Int>): List<Int> {
        val result = mutableListOf(cups[1]!!)
        while (result.last() != 1) {
            result.add(cups[result.last()]!!)
        }
        return result.dropLast(1)
    }

    fun round(current: Int, cups: HashMap<Int, Int>): Pair<Int, HashMap<Int, Int>> {
        val picked = (0 until 3).fold(listOf(current)) { acc, _ -> acc + cups[acc.last()]!! }.drop(1)
        cups[current] = cups[picked.last()]!!
        picked.forEach {
            cups.remove(it)
        }

        var destination = if (current - 1 <= 0) (cups.size + 3) else current - 1
        while (picked.contains(destination)) {
            destination = if (destination -1 ==  0) (cups.size + 3) else destination - 1
        }

        picked.forEachIndexed { i, d ->
            cups[d] = if (i == picked.size - 1) cups[destination]!! else picked[i + 1]!!
        }
        cups[destination] = picked.first()

        return cups[current]!! to cups
    }
}