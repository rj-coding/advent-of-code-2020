package nl.rjcoding.aoc2020

sealed class Instruction

data class Acc(val amount: Int): Instruction()
data class Jmp(val offset: Int): Instruction()
data class Nop(val data: Int) : Instruction()

object Day8 : Day {
    override fun part1(): Long = Util.readInputToLines("day8.txt")
        .map(::parseLine).toList()
        .let { code -> execute(code) }
        .let { states -> takeUntilLoop(states) }
        .last().acc

    override fun part2(): Long = Util.readInputToLines("day8.txt")
        .map(::parseLine).toList()
        .let { code -> execute(fix(code)!!) }
        .last().acc

    fun parseLine(line: String): Instruction = line
        .split(" ")
        .let { (op, data) -> when (op) {
            "acc" -> Acc(data.toInt())
            "jmp" -> Jmp(data.toInt())
            else -> Nop(data.toInt())
            }
        }

    fun takeUntilLoop(states: Sequence<State>): List<State>  {
       val parsedLines = mutableSetOf<Int>()
       val lines = mutableListOf<State>()
       for (state in states) {
           if (parsedLines.contains(state.currentLine)) break
           parsedLines.add(state.currentLine)
           lines.add(state)
       }
        return lines
    }

    fun isCorrupt(code: List<Instruction>): Boolean {
        val states = execute(code)
        val parsedLines = mutableSetOf<Int>()
        for (state in states) {
            if (parsedLines.contains(state.currentLine)) return true
            parsedLines.add(state.currentLine)
        }
        return false
    }

    fun execute(code: List<Instruction>): Sequence<State> = sequence {
        var currentLine = 0
        var accumulator = 0L
        while (currentLine < code.size) {
            val instruction = code[currentLine]!!
            when (instruction) {
                is Acc -> {
                    accumulator += instruction.amount
                    yield(State(currentLine++, currentLine, accumulator))
                }

                is Jmp -> {
                    currentLine += instruction.offset
                    yield(State(currentLine - instruction.offset, currentLine, accumulator))
                }

                is Nop -> yield(State(currentLine++, currentLine, accumulator))
            }

        }
    }

    fun fix(code: List<Instruction>): List<Instruction>? {
        if (!isCorrupt(code)) return code

        val opsToFlip = code
            .mapIndexed { line, instruction -> line to instruction }
            .filter { (_, instruction) -> instruction is Jmp || instruction is Nop }

        opsToFlip.forEach { (line, instuction)  ->
            val newCode = code.subList(0, line) + flip(instuction) + code.subList(line + 1, code.size)
            if (!isCorrupt(newCode)) return newCode
        }

        return null
    }

    private fun flip(instruction: Instruction) = when (instruction) {
        is Acc -> instruction
        is Jmp -> Nop(instruction.offset)
        is Nop -> Jmp(instruction.data)
    }

    data class State(val currentLine: Int, val nextLine: Int, val acc: Long)
}