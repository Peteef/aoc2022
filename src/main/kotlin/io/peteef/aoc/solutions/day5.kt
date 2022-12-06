package io.peteef.aoc.solutions

import io.peteef.aoc.DescribedSolution
import io.peteef.aoc.readFile

val DAY5: DescribedSolution = "Day 5" to ::solve

private const val EMPTY_SPACE: StorageItem = '-'

private val INSTRUCTION_REGEX: Regex = "move (?<quantity>\\d*) from (?<from>\\d*) to (?<to>\\d*)".toRegex()

private typealias StorageItem = Char
private typealias Layer = List<StorageItem>
private typealias Stack = ArrayDeque<StorageItem>
private typealias Storage = List<Stack>

private fun solve() {
    val rawData = "/input/input_5_1.dat".readFile()

    val split = rawData.split("\n\n")
    val rawStorage = split[0]
    val rawInstructions = split[1]

    val instructions = rawInstructions.lines()
        .filter(String::isNotBlank)
        .map(::toInstruction)

    val storage: Storage = setupStorage(rawStorage)
    val afterOperating = instructions.map { storage.operate(it) }.last()
    println("Top of the storage is ${afterOperating.check()}")

    val improvedStorage: Storage = setupStorage(rawStorage)
    val afterImprovedOperating = instructions.map { improvedStorage.operateMovingAll(it) }.last()
    println("Top of the storage after improvement is ${afterImprovedOperating.check()}")
}

private fun initStorage(input: String): Storage = input.lines()
    .last()
    .split("   ")
    .map(String::trim)
    .filter(String::isNotEmpty)
    .maxOf(String::toInt)
    .let { List(it) { ArrayDeque() } }

private fun setupStorage(input: String): Storage =
    with(initStorage(input)) {
        input.lines()
            .reversed()
            .drop(1)
            .map(::readLayer)
            .fold(this) { accumulator, next ->
                accumulator.mergeWith(next)
            }
    }

private fun readLayer(input: String): Layer =
    input.chunked(4)
        .map { it.trim() }
        .map { if (it.isNotEmpty()) it[1] else EMPTY_SPACE }

private fun Storage.mergeWith(layer: Layer): Storage =
    this.mapIndexed { index, stack -> stack.push(layer[index]) }

private fun Stack.push(item: StorageItem): Stack =
    this.apply { if (item != EMPTY_SPACE) this.addLast(item) }

private fun toInstruction(input: String): Instruction =
    INSTRUCTION_REGEX.matchEntire(input)
        ?.destructured
        ?.let { (quantity, from, to) -> Instruction(quantity.toInt(), from.toInt(), to.toInt()) }
        ?: throw IllegalArgumentException("Bad instruction input: $input")

private fun Storage.operate(instruction: Instruction): Storage =
    apply {
        repeat(instruction.quantity) {
            this[instruction.from - 1].removeLast().also {
                this[instruction.to - 1].addLast(it)
            }
        }
    }

private fun Storage.operateMovingAll(instruction: Instruction): Storage =
    apply {
        (0 until instruction.quantity).map { this[instruction.from - 1].removeLast() }
            .reversed()
            .also { this[instruction.to - 1].addAll(it) }
    }

private fun Storage.check(): String = this.map(Stack::last).joinToString(separator = "")

private data class Instruction(val quantity: Int, val from: Int, val to: Int)