package io.peteef.aoc

import io.peteef.aoc.solutions.DAY1

object App {

    private val SOLUTIONS: IterableSolutions = mapOf(
        DAY1
    )

    @JvmStatic
    fun main(args: Array<String>) {
        println("/test_hello.dat".readFile())
        SOLUTIONS.forEach { printSolution(it.key, it.value) }
    }
}