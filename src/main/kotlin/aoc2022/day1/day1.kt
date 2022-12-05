package aoc2022.day1

import utils.FileReader

fun getSums(): List<Int> {
    val file = FileReader.readAsText("aoc2022/day1")
    val groups: List<List<Int>> = file.split("\r\n\r\n").map { it.split("\r\n").map { it.toInt() } }
    return groups.map { it.sum() }.sorted().reversed()
}

fun partOne() {
    println(getSums()[0])
}

fun partTwo() {
    println(getSums().subList(0, 3).sum())
}

fun main() {
    partOne()
    partTwo()
}