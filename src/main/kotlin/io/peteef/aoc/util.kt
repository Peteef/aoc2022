package io.peteef.aoc

import java.io.File
import java.io.InputStream

fun String.asFileStream(): InputStream = App::class.java.getResourceAsStream(this) ?: File(this).inputStream()

fun String.readFileLines(): List<String> = this.asFileStream().bufferedReader().readLines()

fun String.readFile(): String = this.asFileStream().bufferedReader().readText()

fun printSolution(header: String, solution: Solution) {
    println(
        """
            ########################
            $header
            ########################
            
        """.trimIndent()
    )
    solution()
    println()
}