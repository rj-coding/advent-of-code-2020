package nl.rjcoding.aoc2020

object Util {
    fun readInputToLines(fileName: String): Sequence<String> {
        return javaClass.classLoader.getResourceAsStream("day1.txt")!!.bufferedReader().lineSequence()
    }
}