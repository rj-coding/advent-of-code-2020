package nl.rjcoding.aoc2020

object Util {
    fun readInputToLines(fileName: String): Sequence<String> {
        return javaClass.getResourceAsStream("/${fileName}")!!.bufferedReader().lineSequence()
    }
}