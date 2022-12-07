package io.peteef.aoc.solutions

import io.peteef.aoc.DescribedSolution
import io.peteef.aoc.readFile

val DAY6: DescribedSolution = "Day 6" to ::solve

private const val PACKER_MARKER_DISTINCT_ELEMENTS: Int = 4
private const val MESSAGE_MARKER_DISTINCT_ELEMENTS: Int = 14

typealias SignalElement = Char
typealias Signal = List<SignalElement>

private fun solve() {
    val rawInput = "/input/input_6_1.dat".readFile()

    val signal: Signal = rawInput.toList()

    val packetMarker = signal.findMarkerPosition(PACKER_MARKER_DISTINCT_ELEMENTS)
    println("Start of packet marker position is: $packetMarker")

    val messageMarker = signal.findMarkerPosition(MESSAGE_MARKER_DISTINCT_ELEMENTS)
    println("Start of message marker position is: $messageMarker")
}

private fun Signal.findMarkerPosition(distinctElements: Int): Int =
    this.indices
        .drop(distinctElements)
        .takeWhile { !this.subList(it - distinctElements, it).hasUniqueElements() }
        .max() + 1

private fun Signal.hasUniqueElements(): Boolean = this.size == this.distinct().size