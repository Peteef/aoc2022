package io.peteef.aoc

import io.peteef.aoc.solutions.day1

object App {

    private val SOLUTIONS: Map<String, Solution>  = mapOf(
        day1()
    )

    @JvmStatic
    fun main(args: Array<String>) {
        println("/test_hello.dat".readFile())
        SOLUTIONS.forEach { printSolution(it.key, it.value) }
    }
}