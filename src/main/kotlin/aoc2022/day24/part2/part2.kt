package aoc2022.day24.part2

import utils.Grid
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
val startPoint = Point(1, 0)
val endPoint = Point(length - 1, height)

data class Moves(
    val moves: Int = 0,
    val position: Point = startPoint,
    var atEndpoint: Boolean = false,
    var atStart: Boolean = false
) {
    fun getStatus(): Status {
        return Status(atEndpoint, atStart, position)
    }
}

data class Status(
    val atEndpoint: Boolean,
    val atStart: Boolean,
    val point: Point
)

val movesList = mutableListOf(Moves())
fun main() {
    grid.getFields().filter { it.value.isIn(blizzardSymbols) }.forEach { it.value = "." }
    gridCopy.getFields().filter { it.value.isIn(blizzardSymbols) }.forEach { it.value = "." }
    var finished = false
    while (!finished) {
        val currentMoves = movesList.toList()
        moveBlizzards()
        currentMoves.forEach {
            it.doRound()
            val groupedMoves = movesList.groupBy { it.getStatus() }.filter { it.value.size > 1 }
            groupedMoves.keys.forEach { duplicated ->
                movesList.removeAll {
                    it.position == duplicated.point && it.atStart == duplicated.atStart && it.atEndpoint == duplicated.atEndpoint
                }
            }
            groupedMoves.values.forEach {
                movesList.add(it[0])
            }
        }

        val atEndpoint = movesList.filter { it.position == endPoint }
        if (atEndpoint.isNotEmpty()) {
            atEndpoint.forEach { it.atEndpoint = true }
            movesList.removeAll { !it.atEndpoint }
        }
        val atStart = movesList.filter { it.position == startPoint && it.atEndpoint }
        if (atStart.isNotEmpty()) {
            atStart.forEach { it.atStart = true }
            movesList.removeAll { !it.atStart }
        }
        if (movesList.any { it.atStart && it.position == endPoint }) {
            finished = true
            println(movesList.first { it.atEndpoint && it.position == startPoint })
            println(movesList.first { it.atEndpoint && it.position == startPoint }.moves)
        }
    }

}

private fun Moves.doRound() {
    if (position.addX(1).isClear()) movesList.add(this.copy(moves = this.moves + 1, position = position.addX(1)))
    if (position.addX(-1).isClear()) movesList.add(this.copy(moves = this.moves + 1, position = position.addX(-1)))
    if (position.addY(1).isClear()) movesList.add(this.copy(moves = this.moves + 1, position = position.addY(1)))
    if (position.addY(-1).isClear()) movesList.add(this.copy(moves = this.moves + 1, position = position.addY(-1)))
    if (position.isClear()) movesList.add(this.copy(moves = this.moves + 1))
    movesList.remove(this)
}

private fun Point.isClear(): Boolean {
    if (this.y == -1) return false
    if (this.y == height + 1) return false
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
