package nl.rjcoding.aoc2020

object Util {

    fun readInput(fileName: String): String {
        return javaClass.getResource("/${fileName}").readText()
    }

    fun readInputToLines(fileName: String): Sequence<String> {
        return javaClass.getResourceAsStream("/${fileName}")!!.bufferedReader().lineSequence()
    }
}

fun <T> Sequence<T>.takeWhileInclusive(pred: (T) -> Boolean): Sequence<T> {
    var shouldContinue = true
    return takeWhile {
        val result = shouldContinue
        shouldContinue = pred(it)
        result
    }
}