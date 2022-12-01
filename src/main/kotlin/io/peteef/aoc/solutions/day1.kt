package io.peteef.aoc.solutions

import io.peteef.aoc.DescribedSolution
import io.peteef.aoc.readFile

val DAY1: DescribedSolution = "Day 1" to ::solve

private fun solve() {
    val rawInput = "/input/input_1_1.dat".readFile()

    val calories = rawInput.split("\n\n")
        .map { it.split("\n").filter(String::isNotEmpty).sumOf(String::toInt) }

    val max = calories.max()
    println("Max calories is $max")

    val threeHighestCombined = calories.sorted()
        .takeLast(3)
        .sum()
    println("Three highest have combined $threeHighestCombined")
}