package aoc2022.day24.part1

import utils.Grid
import utils.ListUtils.Companion.addReturn
import utils.Point
import utils.StringUtils.Companion.isIn

private const val TEST = """#.######
#>>.<^<#
#.<..<<#
#>v.><>#
#<^v^^>#
######.#"""
private const val INPUT =
    """#.######################################################################################################################################################
#>.^v.>^v>><>>^>>v>v^>.<>^.>v^v>..<<><>>>v.><^<.>^.<^>^v^>>v<^>><.^<^.<v>v><><^^<^>>^<<<>.>.>^^^<^<^>.>^v.>.>^>^v<<^^<>><vv<><>>>v^.>^<vvv>.^>v.>.v<v.<#
#<^^^><<vv<vv^^><>><>vv.vv<>vvvv<vv.>^>^>v^vvv>.<^>v<>>.<><<><>v<v^^<^.v^^><<><^><<.v.vv<<><.<<<<v^^>><^<><^<<<<.>v<^v<v>^>>>><.v>^>.>^<^>>v<v<^vv<v>v<#
#>>>>^.>.^v>v<.^<^vv^^^<>.>>.>><<.v<v^><^><.<<<><.^v^^v<><v^v><vv<<v<..<<^<.<>^<^v.^>^<v^v.^<v>v<><>>>^v<v.^^<><>><>v<^v>>>^v<v^>v<^vvv><v>^><<><^v>>^>#
#><<>^>v>.<^^v><v.<>v^<<<>^^v>^.v<<>><>v.v>v^.v>>>^<^><.>>v<<<>vv^.>^>^<><^.>.v^^<v<.^>^><>^<v>vv^v>v^<>><<vv>>>v^^<^v>>>v.<<>.v<><^^v<.^>v>.<^^><vv.^>#
#<.v^vv^vv>><v>^>>^.vvv^^<v<.v^>><<>v<v<<^<<<vv.>><>v.^>vvv<v<v>^<>>v><.>^.>.^<<<.^vvv>^..^>>.v<v.^v^<^^<<^<^>^v>^v<vvv.v^^v<<<>^v<<<v><>v^<<^><.v>v<v>#
#<^v^><>v^<.>vv>.^<<<vv^^<><^<^.^^<.^vv^>v^^.^<^>^.^<><><^<<>><vv<v^<>v<><>v^^v<.<v^<v>vvv>^>>^<^><>>^vv<^>><v><^.<<v><v.>^v^v>>>^<vv>v<^<><>.^^>.v><<>#
#<.><^v>^<.>>v<v>v<^v^v.^>v<.v>.<vv>^>v<v<<>^^.<>.<.vvv<^>v^.^<^v>^.>.v<.v>><<<^vv^.^<<^<^v<v^^^>.><>v.^^.^><<.<vvvv<v><<^vv.<^^^^>^<.<><<>>^>v>.^^v.<<#
#<<^.>v<><>.^>>^v<<^>vv<^v>><.<v^v^<<><v><^^.^^^>^.><^>v^v<<<<>>^>v>^v<^<>..v<.<v<><>v^>v^^v..<>^<><>^v<<v<<><<.v>...>^^v>.>v^.^>vv>^v<>v><<^.>^^..<<^<#
#><v^<^..>>v<v.<>vvv>v<v^>>>.v^<^v<>.>^<.<<^<>.<..>.^.>^^>.^.^^>v.v^>v<^>^v.<v^>v^^^vv><^>^>>>v><v<>>>..v.<^<.<<<>>><^<<v>>^<.>^^^<><^<.<^>^<v.^>vv>vv>#
#<><>v^..>v>^^^^<.v>v>..<>><^><<^v.v.<v^<v><><<<v>>>^^>>v<<>^^<^^^>v.<<>v^<v><v^<<v>>^v>>v><v^^>vv^<^v<^v^.><>vv>>>.>>v^<v^v>><^^v.^<v.><v^^<<<v^>>^v^>#
#>v><>><<^.>v^.v<<<v><^vv<.v^>v<.>vv>v>>^<^<<><>v>v<v..v<^<.vvv<^>^^v<^.>.^^^>^^v.^.>^>>>^><v<.v<^^^^.v.vv<<vv<.>v<^>v^<<vv^v><v>v^>>v.^.^<<.vv>^^^<^v<#
#<v^<>>^>>^..vv<^<<<v.v.^vv<.<^^<v^<.>^<.^.<<v^^^<^><>.>>>><^<>^>^v>><v^>v.<.^<v>^^>><><.>^v^>^<>^<^^.<^>^^^^^^vv<>vv^v^v>^v^<v<vv><<vv<^^^.>>^><<<.^>>#
#<.<v^.>>><vv^.<^^^>>>.><v<v>^<>>.^^vv>>^<<<^v><^^<v<>>^^^^<v^<>^^>><^>v^<<>>v<^.>vvvv>>^<.^>.v<^^<<v>>.vv.v^v<>>>><^v^>>>.v.^^v<<v>v^^>^v>^v<><.v><^v.#
#><<^v><<<.v<^^><^v<^v.<<<.^^.v>vvvv>^>><^^v<>.<>><<.v^>..^<>.><^>^>^v^^>v<<vv>^>vvv.>^^>^^<<>v>v^>^^^<^v>.v>^<><^<.v<^><<>^.^>>><<v^>v^v^v<^v>>^^^><^<#
#<^>v<>.>>^.v>^<v><>.v.^^v>^<..v.^>^>.v>>><vv^<>^^>v><>vv.<v<v^vv<.^vv<><v>>>^v><v^vvvvv.<vv>>^v<<.v>v^>v><<<^.<<<<>vv>^>>^<>^^<vvv^<vvvvvv><^^<<<>^^<>#
#>>.>>.^>..<^<^.<<v<><>..^^<..v<.><^^<v.v^v><>^vv<v<^v<^>.v<vvvv><<<>^>^v^>v<>><vv^v<>>>>>v<v<>^.>><v<v.>><v<v^^<v.<><.<>^^^^v<<^>>.><<.><.v^^..>>>.v>>#
#>v<v^^<^vvv.>^<>>v^v.vv^>v^vvvvv<>.v^.v<.><>^>>^<<v<^^v>v><^.>>^><>v<vv>v<<^>^>^.<v>vv<<>^^>^<<>v><v>.<<^.<>><<^<<.^<^<<<.^>.<.vv>v^v<^>vvv>^v<<<<v<<<#
#<^<>v^.^<><>^<>^>v<v.^v..v..vv^.<.^^>^>^><.vv>>.<^..^^^<^<.^^<^^v^<^.<<<<<v<^<<><v>^v.<<>.^>><^>^v>><<v<>^v^<<^>>v<<^.v<<^vv>>v>^.v^^^<><vv^^<<>.v.<><#
#..vv<v><^vv>.v<v<^v><vvv>.v>^v^>>vv^.^>v.v^^<<>^^<>^<^^>.<^v<v>^>^<v<<v<>v<^^^>v>><><<><.<<><^<v<<>v^^>>v><>^v^.v>^^v<^><><^v<^<vv.vvv><><<>v<<><>v<.>#
#>>.<^^>><<<^>v<^^>>^<<>><<.<^^^<^^^<^<v<^<<^><<v<v<^^>vv<<^v>.^<^^>><>^^^.>v>v^<^>>v><<^<^^^>v><<>^^v><>v..<.^^>..vv.<v>>>^<<v^<v^^v>>>>.>vv.>>^v>^v<.#
######################################################################################################################################################.#"""

val grid = Grid(INPUT)
val gridCopy = Grid(TEST)
val length = grid.rows[0].size - 1
val height = grid.rows.size - 1
val endPoint = Point(length - 1, height)
val moves = mutableListOf(mutableListOf(Point(1, 0)))
fun main() {
    grid.getFields().filter { it.value.isIn(blizzardSymbols) }.forEach { it.value = "." }
    var finished = false
    while (!finished) {
        val currentMoves = moves.toList()
        moveBlizzards()
        currentMoves.forEach {
            if (it.doRound()) finished = true
            moves.removeAll { it.size < moves.maxBy { it.size }.size }
            val groupedMoves = moves.groupBy { it.last() }.filter { it.value.size == 2 }
            groupedMoves.keys.forEach { duplicated -> moves.removeAll { it.last() == duplicated } }
            groupedMoves.values.forEach {
                moves.add(it[0])
            }
        }
    }
    println(moves.filter { it.last() == endPoint })
    println(moves.first { it.last() == endPoint }.size - 1)
}

private fun List<Point>.doRound(): Boolean {
    val position = this.last()
    if (position.addX(1).isClear()) moves.add(this.toMutableList().addReturn(position.addX(1)))
    if (position.addX(-1).isClear()) moves.add(this.toMutableList().addReturn(position.addX(-1)))
    if (position.addY(1).isClear()) moves.add(this.toMutableList().addReturn(position.addY(1)))
    if (position.addY(-1).isClear()) moves.add(this.toMutableList().addReturn(position.addY(-1)))
    if (position.isClear()) moves.add(this.toMutableList().addReturn(position))
    moves.remove(this)

    return moves.any { it.last() == endPoint }
}

private fun Point.isClear(): Boolean {
    if (this.y == -1) return false
    return grid.at(this).value == "." && blizzards.none { it.x == this.x && it.y == this.y }
}

data class Blizzard(var x: Int, var y: Int, var value: String)

val blizzardSymbols = listOf("<", ">", "^", "v")
val blizzards = grid.getFields().filter { it.value.isIn(blizzardSymbols) }.map { Blizzard(it.x, it.y, it.value) }
private fun moveBlizzards() {
    blizzards.forEach { blizzard ->
        when (blizzard.value) {
            "<" -> if (blizzard.x == 1) blizzard.x = length - 1 else blizzard.x -= 1
            ">" -> if (blizzard.x == length - 1) blizzard.x = 1 else blizzard.x += 1
            "v" -> if (blizzard.y == height - 1) blizzard.y = 1 else blizzard.y += 1
            "^" -> if (blizzard.y == 1) blizzard.y = height - 1 else blizzard.y -= 1
        }
    }
}
