package aoc2022.day19.part2

import utils.ListUtils.Companion.prod

data class Blueprint(
    val oreRobotCost: Int, val clayRobotCost: Int,
    val obsidianRobotCost: Pair<Int, Int>, val geodeRobotCost: Pair<Int, Int>
) {
    fun maxOreRobots(): Int {
        return maxOf(oreRobotCost, clayRobotCost, obsidianRobotCost.first, geodeRobotCost.first)
    }
}

data class Strategy(
    val blueprint: Blueprint,

    var oreRobots: Int = 1,
    var clayRobots: Int = 0,
    var obsidianRobots: Int = 0,
    var geodeRobots: Int = 0,

    var ore: Int = 0,
    var clay: Int = 0,
    var obsidian: Int = 0,
    var geode: Int = 0
) {
    fun addMinerals() {
        this.ore += this.oreRobots
        this.clay += this.clayRobots
        this.obsidian += this.obsidianRobots
        this.geode += this.geodeRobots
    }

    fun couldBuyOreRobot(): Boolean {
        return ore >= blueprint.oreRobotCost && oreRobots <= blueprint.maxOreRobots()
                && ore < blueprint.oreRobotCost + oreRobots
    }

    fun couldBuyClayRobot(): Boolean {
        return ore >= blueprint.clayRobotCost && clayRobots <= blueprint.obsidianRobotCost.second
                && ore < blueprint.clayRobotCost + oreRobots
    }

    fun couldBuyObsidianRobot(): Boolean {
        return ore >= blueprint.obsidianRobotCost.first &&
                clay >= blueprint.obsidianRobotCost.second && obsidianRobots <= blueprint.geodeRobotCost.second
    }

    fun couldBuyGeodeRobot(): Boolean {
        return ore >= blueprint.geodeRobotCost.first &&
                obsidian >= blueprint.geodeRobotCost.second
    }

    fun buyOreRobot() {
        this.oreRobots += 1
        this.ore -= blueprint.oreRobotCost
    }

    fun buyClayRobot() {
        this.clayRobots += 1
        this.ore -= blueprint.clayRobotCost
    }

    fun buyObsidianRobot() {
        this.obsidianRobots += 1
        this.ore -= blueprint.obsidianRobotCost.first
        this.clay -= blueprint.obsidianRobotCost.second
    }

    fun buyGeodeRobot() {
        this.geodeRobots += 1
        this.ore -= blueprint.geodeRobotCost.first
        this.obsidian -= blueprint.geodeRobotCost.second
    }
}

fun main() {
    val blueprints = INPUT.split("\n").map { Regex("\\d+").findAll(it).map { it.value.toInt() }.toList() }
        .map {
            Blueprint(
                it[1], it[2], Pair(it[3], it[4]), Pair(it[5], it[6])
            )
        }
    val sums = blueprints.mapIndexed { index, blueprint ->
        var strategies = setOf(Strategy(blueprint))
        repeat(32) {
            strategies = doRound(strategies)
        }
        strategies.maxByOrNull { it.geode }!!.geode
    }
    println(sums)
    println(sums.size)
    println(sums.prod())
}

fun doRound(strategies: Set<Strategy>): Set<Strategy> {
    val newStrategies = mutableSetOf<Strategy>()
    strategies.forEach {
        if (it.couldBuyGeodeRobot()) {
            val new = it.copy(); new.addMinerals(); new.buyGeodeRobot(); newStrategies.add(new)
        }
        if (it.couldBuyOreRobot()) {
            val new = it.copy(); new.addMinerals(); new.buyOreRobot(); newStrategies.add(new)
        }
        if (it.couldBuyClayRobot()) {
            val new = it.copy(); new.addMinerals(); new.buyClayRobot(); newStrategies.add(new)
        }
        if (it.couldBuyObsidianRobot()) {
            val new = it.copy(); new.addMinerals(); new.buyObsidianRobot(); newStrategies.add(new)
        }

        it.addMinerals()
        newStrategies.add(it)
    }

    return newStrategies
}

private const val INPUT =
    """Blueprint 1: Each ore robot costs 2 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 15 clay. Each geode robot costs 2 ore and 20 obsidian.
Blueprint 2: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 4 ore and 20 clay. Each geode robot costs 2 ore and 8 obsidian.
Blueprint 3: Each ore robot costs 4 ore. Each clay robot costs 4 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 4 ore and 8 obsidian."""

private const val TEST =
    """Blueprint1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian."""