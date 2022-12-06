package io.peteef.aoc.solutions

import io.peteef.aoc.DescribedSolution
import io.peteef.aoc.readFileLines
import io.peteef.aoc.solutions.Move.*
import io.peteef.aoc.solutions.Result.*

val DAY2: DescribedSolution = "Day 2" to ::solve

private enum class Move(val points: Int) {
    ROCK(1), PAPER(2), SCISSORS(3),
}

private enum class Result(val points: Int) {
    LOSE(0), DRAW(3), WIN(6)
}

private fun solve() {
    val games = "/input/input_2_1.dat".readFileLines()

    val sum = games.map(::toMoves).sumOf { calculateScore(it.first, it.second) }
    println("Sum of points is $sum")

    val alterSum = games.map(::toMoveAndResult)
        .sumOf { calculateScore(it.first, calculateMove(it.first, it.second)) }
    println("Sum of points after changes is $alterSum")
}

private fun toMoves(game: String): Pair<Move, Move> =
    game.split(" ")
        .let { it[0].toMove() to it[1].toMove() }

private fun String.toMove(): Move = when (this) {
    "A", "X" -> ROCK
    "B", "Y" -> PAPER
    "C", "Z" -> SCISSORS
    else -> throw IllegalArgumentException("Illegal input $this")
}

private fun calculateScore(enemy: Move, me: Move): Int =
    when (enemy) {
        ROCK -> when (me) {
            ROCK -> DRAW
            PAPER -> WIN
            SCISSORS -> LOSE
        }

        PAPER -> when (me) {
            ROCK -> LOSE
            PAPER -> DRAW
            SCISSORS -> WIN
        }

        SCISSORS -> when (me) {
            ROCK -> WIN
            PAPER -> LOSE
            SCISSORS -> DRAW
        }
    }.let { it.points + me.points }

private fun String.toResult(): Result = when (this) {
    "X" -> LOSE
    "Y" -> DRAW
    "Z" -> WIN
    else -> throw IllegalArgumentException("Illegal input $this")
}

private fun toMoveAndResult(game: String): Pair<Move, Result> =
    game.split(" ")
        .let { it[0].toMove() to it[1].toResult() }

private fun calculateMove(enemy: Move, result: Result): Move =
    when (enemy) {
        ROCK -> when (result) {
            LOSE -> SCISSORS
            DRAW -> ROCK
            WIN -> PAPER
        }

        PAPER -> when (result) {
            LOSE -> ROCK
            DRAW -> PAPER
            WIN -> SCISSORS
        }

        SCISSORS -> when (result) {
            LOSE -> PAPER
            DRAW -> SCISSORS
            WIN -> ROCK
        }
    }