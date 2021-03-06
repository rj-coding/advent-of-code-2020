package nl.rjcoding.aoc2020

fun <T> Sequence<T>.takeWhileInclusive(pred: (T) -> Boolean): Sequence<T> {
    var shouldContinue = true
    return takeWhile {
        val result = shouldContinue
        shouldContinue = pred(it)
        result
    }
}

fun Iterable<Long>.minMax(): Pair<Long, Long> = this.fold(Long.MAX_VALUE to Long.MIN_VALUE) { (min, max), v ->
    kotlin.math.min(min, v) to kotlin.math.max(max, v)
}

fun Long.toDigits() : List<Int> {
    val digits = mutableListOf<Int>()
    var remaining = this
    while (remaining > 0) {
        digits.add((remaining % 10).toInt())
        remaining /= 10L
    }
    return digits.reversed()
}

fun List<Int>.toLong() : Long = this.fold(0L) { acc, d ->
    acc * 10 + d
}

fun <T> Iterable<T>.combinations(count: Int) = Util.combinations(this, count)

fun <T> T.reduceRepeated(n: Int, block: (T) -> T): T = (0 until n).fold(this) { acc, _ -> block(acc) }

operator fun Long.plus(complex: Complex) = Complex(this + complex.i, complex.j)
operator fun Int.plus(complex: Complex) = Complex(this + complex.i, complex.j)
operator fun Long.minus(complex: Complex) = Complex(this - complex.i, -complex.j)
operator fun Int.minus(complex: Complex) = Complex(this - complex.i, -complex.j)

operator fun Long.times(complex: Complex) = complex * this
operator fun Int.times(complex: Complex) = complex * this