package aoc2022.day16.part1


val valves = createValves()
fun main() {
    val startPath = mutableListOf<Action>(valves.first { it.name == "AA" })
    repeat(30) {
        val newPaths = mutableListOf(mutableListOf<Action>())
        createRoutes(startPath.toMutableList(), startPath.size, newPaths)
        val bestPaths = newPaths.sortedBy { it.pressure() }.reversed().filter { it.size > 0 }
        val top20BestPaths = bestPaths.subList(0, minOf(20, bestPaths.size - 1))
        val extendedPaths = top20BestPaths.flatMap {
            val newNewPaths = mutableListOf(mutableListOf<Action>())
            createRoutes(it, it.size, newNewPaths)
            newNewPaths
        }.sortedBy { it.pressure() }.reversed().filter { it.size > 0 }
        if(extendedPaths.isEmpty()) {
            startPath.add(bestPaths.first()[startPath.size])
            return@repeat
        }
        val top20ExtendPaths = extendedPaths.subList(0, minOf(20, extendedPaths.size - 1))
        val furtherExtendedPaths = top20ExtendPaths.flatMap {
            val newNewPaths = mutableListOf(mutableListOf<Action>())
            createRoutes(it, it.size, newNewPaths)
            newNewPaths
        }.sortedBy { it.pressure() }.reversed().first()

        startPath.add(furtherExtendedPaths[startPath.size])
        println("Record: ${startPath.pressure()}")
    }
    println(startPath.map {
        when (it) {
            is TurnOn -> "On"; is Valve -> it.name; else -> ""
        }
    })
    println(startPath.pressure())
}

fun createRoutes(path: MutableList<Action>, startActions: Int, paths: MutableList<MutableList<Action>>) {
    if (path.size == startActions + 10 || path.size == 31) {
        paths.add(path)
        return
    }
    if (path.last() is TurnOn) {
        return (path[path.size - 2] as Valve).getConnections().forEach {
            createRoutes((path + it).toMutableList(), startActions, paths)
        }
    }
    if ((path.last() as Valve).flowRate > 0 && path.isNotTurnedOn((path.last() as Valve).name)) {
        createRoutes((path + TurnOn()).toMutableList(), startActions, paths)
    }
    (path.last() as Valve).getConnections().forEach {
        createRoutes((path + it).toMutableList(), startActions, paths)
    }
}

fun List<Action>.pressure(): Int {
    return this.mapIndexed { index, actions ->
        when (actions) {
            is TurnOn -> (this[index - 1] as Valve).flowRate * (30 - index)
            else -> 0
        }
    }.sum()
}

fun List<Action>.isNotTurnedOn(name: String): Boolean {
    return this.filterIndexed { index, action ->
        index < this.size - 1 && action is Valve && action.name == name && this[index + 1] is TurnOn
    }.isEmpty()
}

fun List<Action>.description(): String {
    return this.joinToString("") {
        when (it) {
            is Valve -> it.name; is TurnOn -> "On"; else -> ""
        }
    }
}

interface Action
class TurnOn : Action
data class Valve(
    val name: String,
    val flowRate: Int,
    private val connections: List<String>,
    var turnedOn: Boolean = false
) : Action {
    fun getConnections(): List<Valve> {
        return valves.filter { it.name in connections }
    }
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