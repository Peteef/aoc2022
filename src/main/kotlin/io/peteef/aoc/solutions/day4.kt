package io.peteef.aoc.solutions

import io.peteef.aoc.DescribedSolution
import io.peteef.aoc.readFileLines

val DAY4: DescribedSolution = "Day 4" to ::solve

private typealias Range = ClosedRange<Int>
private typealias WorkersPair = Pair<Range, Range>

private fun solve() {
    val rawPairs = "/input/input_4_1.dat".readFileLines()

    val workers = rawPairs.map(::toWorkersPair)

    val fullyOverlappingPairs = workers.count(WorkersPair::fullyOverlaps)
    println("Fully overlapping pairs count are $fullyOverlappingPairs")

    val overlappingPairs = workers.count(WorkersPair::overlaps)
    println("Overlapping pairs count are $overlappingPairs")
}

private fun toWorkersPair(input: String): WorkersPair =
    input.split(",")
        .apply { assert(this.size == 2) { "Pair should contain 2 workers!" } }
        .let { WorkersPair(toRange(it[0]), toRange(it[1])) }

private fun toRange(input: String): Range =
    input.split("-")
        .apply { assert(this.size == 2) {"Range should contain two elements!"} }
        .map(String::toInt)
        .let { it[0]..it[1] }

private fun WorkersPair.fullyOverlaps(): Boolean =
    this.first.within(this.second) || this.second.within(this.first)

private fun WorkersPair.overlaps(): Boolean =
    this.first.contains(this.second.start) || this.first.contains(this.second.endInclusive) || fullyOverlaps()

private fun Range.within(another: Range): Boolean =
    another.contains(this.start) && another.contains(this.endInclusive)