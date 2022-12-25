package aoc2022.day16.part2

import utils.CollectionUtils.Companion.isIn
import utils.CollectionUtils.Companion.removeDuplicates

data class Valve(
    val name: String,
    val flowRate: Int,
    private val connections: List<String>,
) {
    fun getConnections(): List<Valve> {
        return valves.filter { it.name in connections }
    }
}

val valves = createValves()

data class DuoPath(
    val humanPath: Path = Path(),
    val elephantPath: Path = Path(),
    var points: Int = 0,
    var valvesTurned: MutableMap<String, Boolean> = valves.associate { it.name to false }.toMutableMap()
) {
    fun turned(valve: String): Boolean {
        return this.valvesTurned[valve]!! || valves.find(valve).flowRate == 0
    }

    fun turnOn(valve: String, round: Int): DuoPath {
        this.valvesTurned[valve] = true
        this.points += valves.find(valve).flowRate * (26 - round)
        return this
    }

    fun onValves(): List<String> {
        return valvesTurned.filter { it.value }.map { it.key }
    }

    override fun toString(): String {
        return "human=${humanPath.position.name}, elephant=${elephantPath.position.name}, points=$points, " +
                "valvesTurned=${valvesTurned.filter { it.value }.map { it.key }}"
    }
}

data class Path(
    var points: Int = 0,
    val position: Valve = valves.find("AA"),
    var passedSinceLastTurnOn: MutableList<String> = mutableListOf("AA")
) {
    fun connections(): List<Valve> {
        val connections = position.getConnections().filter { !it.name.isIn(passedSinceLastTurnOn) }
        return connections.ifEmpty { listOf(position) }
    }
}

data class Duplicate(
    val humanPosition: String,
    val elephantPosition: String,
    val valvesTurned: List<String>
)

var paths = mutableListOf(DuoPath())
fun main() {
    IntRange(1, 26).forEach { round ->
        println(paths.size)
        val tempPaths = paths.toList()
        tempPaths.forEach {
            createRoutes(it, round)
        }
        paths.removeDuplicates({
            Duplicate(it.humanPath.position.name, it.elephantPath.position.name,
                it.valvesTurned.filter { it.value }.map { it.key })
        }, Comparator { a, b -> a.points.compareTo(b.points) })
        paths.removeDuplicates({ setOf(it.humanPath.position, it.elephantPath.position, it.points) })
        paths.removeOutperformed()
        if(round % 5 == 0) {
            paths = paths.sortedBy { it.points }.reversed().subList(0, 500).toMutableList()
        }
    }
    println(paths.sortedBy { it.points }.last())
    println(paths.maxByOrNull { it.points }!!.points)
}

fun createRoutes(path: DuoPath, round: Int) {
    if (!path.turned(path.humanPath.position.name) && !path.turned(path.elephantPath.position.name)
        && path.humanPath.position != path.elephantPath.position
    ) {
        val newPath = path.copy(
            valvesTurned = path.valvesTurned.toMutableMap()
        )
        newPath.turnOn(path.humanPath.position.name, round)
        path.humanPath.passedSinceLastTurnOn = mutableListOf(path.humanPath.position.name)
        newPath.turnOn(path.elephantPath.position.name, round)
        path.elephantPath.passedSinceLastTurnOn = mutableListOf(path.elephantPath.position.name)
        paths.add(newPath)
    }
    if (!path.turned(path.humanPath.position.name)) {
        path.elephantPath.connections().forEach {
            val newPath = path.copy(
                elephantPath = path.elephantPath.copy(position = it),
                valvesTurned = path.valvesTurned.toMutableMap()
            )
            newPath.turnOn(path.humanPath.position.name, round)
            path.humanPath.passedSinceLastTurnOn = mutableListOf(path.humanPath.position.name)
            paths.add(newPath)
        }
    }
    if (!path.turned(path.elephantPath.position.name)) {
        path.humanPath.connections().forEach {
            val newPath = path.copy(
                humanPath = path.humanPath.copy(position = it),
                valvesTurned = path.valvesTurned.toMutableMap()
            )
            newPath.turnOn(path.elephantPath.position.name, round)
            path.elephantPath.passedSinceLastTurnOn = mutableListOf(path.elephantPath.position.name)
            paths.add(newPath)
        }
    }
    path.humanPath.connections().forEach { human ->
        path.elephantPath.connections().forEach { elephant ->
            val newPath = path.copy(
                humanPath = path.humanPath.copy(position = human),
                elephantPath = path.elephantPath.copy(position = elephant),
                valvesTurned = path.valvesTurned.toMutableMap()
            )
            newPath.humanPath.passedSinceLastTurnOn = path.humanPath.passedSinceLastTurnOn.toMutableList()
            newPath.elephantPath.passedSinceLastTurnOn = path.elephantPath.passedSinceLastTurnOn.toMutableList()
            newPath.humanPath.passedSinceLastTurnOn.add(human.name)
            newPath.elephantPath.passedSinceLastTurnOn.add(elephant.name)
            paths.add(newPath)
        }
    }
}

fun MutableList<DuoPath>.removeOutperformed() {
    val grouped = this.groupBy { Pair(it.humanPath.position.name, it.elephantPath.position.name) }
        .filter { it.value.size > 1 }
    val obsoletePaths: List<DuoPath> = grouped.values.map { listWithSamePosition ->
        listWithSamePosition.filter { obsolete ->
            listWithSamePosition.any {
                it.onValves().containsAll(obsolete.onValves()) && it.points > obsolete.points
            }
        }
    }.flatten()
    this.removeAll(obsoletePaths)
}

fun createValves(): List<Valve> {
    return INPUT.split("\n").map {
        it.replace("Valve ", "").replace(" has flow rate", "")
            .replace(" tunnels lead to valves ", "")
            .replace(" tunnel leads to valve ", "")
            .split("=|;".toRegex())
    }.map {
        Valve(it[0], it[1].toInt(), it[2].split(",").map { it.trim() })
    }
}

fun List<Valve>.find(name: String): Valve {
    return this.first { it.name == name }
}

private const val TEST = """Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
Valve BB has flow rate=13; tunnels lead to valves CC, AA
Valve CC has flow rate=2; tunnels lead to valves DD, BB
Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
Valve EE has flow rate=3; tunnels lead to valves FF, DD
Valve FF has flow rate=0; tunnels lead to valves EE, GG
Valve GG has flow rate=0; tunnels lead to valves FF, HH
Valve HH has flow rate=22; tunnel leads to valve GG
Valve II has flow rate=0; tunnels lead to valves AA, JJ
Valve JJ has flow rate=21; tunnel leads to valve II"""

private const val INPUT = """Valve OA has flow rate=0; tunnels lead to valves VP, VM
Valve GA has flow rate=13; tunnel leads to valve KV
Valve WD has flow rate=0; tunnels lead to valves SH, XQ
Valve TE has flow rate=0; tunnels lead to valves OY, DO
Valve JR has flow rate=0; tunnels lead to valves TR, LY
Valve JQ has flow rate=0; tunnels lead to valves TD, DZ
Valve VH has flow rate=6; tunnels lead to valves WY, YQ, NU
Valve NX has flow rate=0; tunnels lead to valves XQ, MN
Valve XL has flow rate=0; tunnels lead to valves AA, FA
Valve QY has flow rate=0; tunnels lead to valves NU, DO
Valve KV has flow rate=0; tunnels lead to valves GA, XQ
Valve NK has flow rate=0; tunnels lead to valves XW, XQ
Valve JU has flow rate=0; tunnels lead to valves QH, TB
Valve XZ has flow rate=0; tunnels lead to valves AA, SH
Valve XQ has flow rate=18; tunnels lead to valves GK, NX, WD, KV, NK
Valve VM has flow rate=19; tunnels lead to valves LY, OA, OY, AE
Valve LE has flow rate=0; tunnels lead to valves MN, NS
Valve HO has flow rate=0; tunnels lead to valves GO, QH
Valve PX has flow rate=0; tunnels lead to valves MN, VP
Valve MN has flow rate=4; tunnels lead to valves LE, UX, TB, NX, PX
Valve VB has flow rate=0; tunnels lead to valves XM, AA
Valve VP has flow rate=21; tunnels lead to valves XM, WT, BG, PX, OA
Valve KI has flow rate=15; tunnels lead to valves XU, MT
Valve NU has flow rate=0; tunnels lead to valves QY, VH
Valve WT has flow rate=0; tunnels lead to valves SH, VP
Valve OY has flow rate=0; tunnels lead to valves VM, TE
Valve VS has flow rate=0; tunnels lead to valves QH, SH
Valve XM has flow rate=0; tunnels lead to valves VB, VP
Valve HI has flow rate=17; tunnel leads to valve TD
Valve TB has flow rate=0; tunnels lead to valves JU, MN
Valve BG has flow rate=0; tunnels lead to valves VP, GK
Valve HN has flow rate=16; tunnel leads to valve BO
Valve MT has flow rate=0; tunnels lead to valves KI, BO
Valve OX has flow rate=0; tunnels lead to valves DZ, ZF
Valve QH has flow rate=5; tunnels lead to valves FA, DW, VS, JU, HO
Valve YQ has flow rate=0; tunnels lead to valves VH, AE
Valve DW has flow rate=0; tunnels lead to valves ML, QH
Valve WY has flow rate=0; tunnels lead to valves HS, VH
Valve GO has flow rate=0; tunnels lead to valves HO, DO
Valve UX has flow rate=0; tunnels lead to valves AA, MN
Valve AE has flow rate=0; tunnels lead to valves YQ, VM
Valve DZ has flow rate=9; tunnels lead to valves HS, OX, JQ
Valve NS has flow rate=0; tunnels lead to valves SH, LE
Valve LY has flow rate=0; tunnels lead to valves JR, VM
Valve BO has flow rate=0; tunnels lead to valves HN, MT
Valve HS has flow rate=0; tunnels lead to valves WY, DZ
Valve XW has flow rate=0; tunnels lead to valves NK, AA
Valve DO has flow rate=11; tunnels lead to valves TE, XU, ZF, QY, GO
Valve FA has flow rate=0; tunnels lead to valves XL, QH
Valve AA has flow rate=0; tunnels lead to valves VB, XL, XZ, XW, UX
Valve VW has flow rate=14; tunnel leads to valve ML
Valve SH has flow rate=8; tunnels lead to valves NS, WT, XZ, VS, WD
Valve XU has flow rate=0; tunnels lead to valves DO, KI
Valve ZF has flow rate=0; tunnels lead to valves OX, DO
Valve GK has flow rate=0; tunnels lead to valves XQ, BG
Valve ML has flow rate=0; tunnels lead to valves VW, DW
Valve TD has flow rate=0; tunnels lead to valves HI, JQ
Valve TR has flow rate=25; tunnel leads to valve JR"""