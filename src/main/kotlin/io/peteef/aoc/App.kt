package io.peteef.aoc

import io.peteef.aoc.solutions.*

object App {

    private val SOLUTIONS: IterableSolutions = mapOf(
        DAY1, DAY2, DAY3, DAY4, DAY5
    )

    @JvmStatic
    fun main(args: Array<String>) {
        println("/test_hello.dat".readFile())
        SOLUTIONS.forEach { printSolution(it.key, it.value) }
    }
}