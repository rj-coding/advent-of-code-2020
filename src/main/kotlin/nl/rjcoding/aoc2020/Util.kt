package nl.rjcoding.aoc2020

object Util {

    fun readInput(fileName: String): String {
        return javaClass.getResource("/${fileName}").readText()
    }

    fun readInputToLines(fileName: String): Sequence<String> {
        return javaClass.getResourceAsStream("/${fileName}")!!.bufferedReader().lineSequence()
    }
}