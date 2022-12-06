package io.peteef.aoc.solutions

import io.peteef.aoc.DescribedSolution
import io.peteef.aoc.readFileLines

val DAY3: DescribedSolution = "Day 3" to ::solve

private typealias Item = Char
private typealias Section = List<Item>
private typealias Backpack = Pair<Section, Section>
private typealias Group = Triple<Section, Section, Section>

private fun solve() {
    val rawBackpacks = "/input/input_3_1.dat".readFileLines()

    val sumOfPriorities = rawBackpacks.map(::toBackpack)
        .map(Backpack::findShareItem)
        .map(Item::calculatePriority)
        .sum()
    println("Sum of priorities is $sumOfPriorities")

    val sumOfGroupPriorities = rawBackpacks.asSequence()
        .map(String::toList)
        .chunked(3) { Group(it[0], it[1], it[2]) }
        .map { findSharedItem(it) }
        .map(Item::calculatePriority)
        .sum()
    println("Sum of group priorities is $sumOfGroupPriorities")
}

private fun toBackpack(items: String): Backpack =
    items.length
        .apply { assert(this % 2 == 0) { "Items number should be even!" } }
        .let { Backpack(
            items.substring(0 until it / 2).toList(),
            items.substring(it / 2 until it).toList()
        ) }

private fun Backpack.findShareItem(): Item =
    this.first.find { this.second.contains(it) } ?: '-'

private fun findSharedItem(group: Group): Item =
    group.first.find { group.second.contains(it) && group.third.contains(it) } ?: '-'

private fun Item.calculatePriority(): Int = when {
    isLowerCase() -> this.minus(96).code
    isUpperCase() -> this.minus(38).code
    else -> 0
}
