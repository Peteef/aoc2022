package io.peteef.aoc

import io.peteef.aoc.solutions.DAY1
import io.peteef.aoc.solutions.DAY2
import io.peteef.aoc.solutions.DAY3
import io.peteef.aoc.solutions.DAY4

object App {

    private val SOLUTIONS: IterableSolutions = mapOf(
        DAY1, DAY2, DAY3, DAY4
    )

    @JvmStatic
    fun main(args: Array<String>) {
        println("/test_hello.dat".readFile())
        SOLUTIONS.forEach { printSolution(it.key, it.value) }
    }
}