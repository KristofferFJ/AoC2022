package aoc2022.day14.part1

import utils.IntUtils.Companion.toward
import utils.ListUtils.Companion.printGrid

fun main() {
    val grid: MutableList<MutableList<String>> = IntRange(0, 1000).map {
        IntRange(0, 1000).map {
            " "
        }.toMutableList()
    }.toMutableList()
    val wallInstructions = input.split("\n").map {
        it.split(" -> ")
    }
    for (walls in wallInstructions) {
        for (i in 0 until walls.size - 1) {
            val (fromX, fromY) = walls[i].split(",").map { it.toInt() }
            val (toX, toY) = walls[i + 1].split(",").map { it.toInt() }
            for (x in fromX toward toX) {
                grid[fromY][x] = "X"
            }
            for (y in fromY toward toY) {
                grid[y][fromX] = "X"
            }
        }
    }

    fun dropSand(): Int {
        var x = 500; var y = 0; var droppedSands = 0
        while (true) {
            if(y == 900) break
            if(grid[y + 1][x] == " ") { y += 1; continue }
            if(grid[y + 1][x - 1] == " ") { y += 1; x -= 1; continue}
            if(grid[y + 1][x + 1] == " ") { y += 1; x += 1; continue}
            grid[y][x] = "O"
            droppedSands += 1
            x = 500; y = 0
        }
        return droppedSands
    }

    println(dropSand())
    println(grid.printGrid())
}

val inputTest = """498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9"""

val input = """498,13 -> 498,16 -> 496,16 -> 496,20 -> 509,20 -> 509,16 -> 502,16 -> 502,13
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
481,79 -> 485,79
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
493,85 -> 497,85
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
499,85 -> 503,85
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
482,107 -> 487,107
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
505,129 -> 505,131 -> 504,131 -> 504,135 -> 517,135 -> 517,131 -> 509,131 -> 509,129
496,82 -> 500,82
505,129 -> 505,131 -> 504,131 -> 504,135 -> 517,135 -> 517,131 -> 509,131 -> 509,129
489,148 -> 489,149 -> 501,149 -> 501,148
482,49 -> 487,49
488,24 -> 488,25 -> 499,25 -> 499,24
494,61 -> 494,63 -> 490,63 -> 490,70 -> 499,70 -> 499,63 -> 498,63 -> 498,61
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
500,138 -> 500,142 -> 498,142 -> 498,146 -> 513,146 -> 513,142 -> 506,142 -> 506,138
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
489,148 -> 489,149 -> 501,149 -> 501,148
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
498,13 -> 498,16 -> 496,16 -> 496,20 -> 509,20 -> 509,16 -> 502,16 -> 502,13
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
478,82 -> 482,82
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
493,79 -> 497,79
471,52 -> 476,52
469,58 -> 474,58
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
469,103 -> 469,104 -> 484,104 -> 484,103
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
494,61 -> 494,63 -> 490,63 -> 490,70 -> 499,70 -> 499,63 -> 498,63 -> 498,61
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
479,29 -> 489,29 -> 489,28
475,85 -> 479,85
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
490,76 -> 494,76
486,109 -> 491,109
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
489,49 -> 494,49
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
505,129 -> 505,131 -> 504,131 -> 504,135 -> 517,135 -> 517,131 -> 509,131 -> 509,129
488,24 -> 488,25 -> 499,25 -> 499,24
476,58 -> 481,58
476,111 -> 481,111
490,82 -> 494,82
483,111 -> 488,111
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
488,24 -> 488,25 -> 499,25 -> 499,24
479,56 -> 484,56
500,138 -> 500,142 -> 498,142 -> 498,146 -> 513,146 -> 513,142 -> 506,142 -> 506,138
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
473,113 -> 478,113
480,113 -> 485,113
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
481,85 -> 485,85
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
498,13 -> 498,16 -> 496,16 -> 496,20 -> 509,20 -> 509,16 -> 502,16 -> 502,13
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
490,111 -> 495,111
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
468,54 -> 473,54
484,76 -> 488,76
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
505,129 -> 505,131 -> 504,131 -> 504,135 -> 517,135 -> 517,131 -> 509,131 -> 509,129
483,58 -> 488,58
498,13 -> 498,16 -> 496,16 -> 496,20 -> 509,20 -> 509,16 -> 502,16 -> 502,13
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
465,56 -> 470,56
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
481,45 -> 486,45
494,61 -> 494,63 -> 490,63 -> 490,70 -> 499,70 -> 499,63 -> 498,63 -> 498,61
494,113 -> 499,113
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
489,148 -> 489,149 -> 501,149 -> 501,148
472,56 -> 477,56
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
500,138 -> 500,142 -> 498,142 -> 498,146 -> 513,146 -> 513,142 -> 506,142 -> 506,138
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
475,54 -> 480,54
485,47 -> 490,47
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
498,13 -> 498,16 -> 496,16 -> 496,20 -> 509,20 -> 509,16 -> 502,16 -> 502,13
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
500,138 -> 500,142 -> 498,142 -> 498,146 -> 513,146 -> 513,142 -> 506,142 -> 506,138
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
505,129 -> 505,131 -> 504,131 -> 504,135 -> 517,135 -> 517,131 -> 509,131 -> 509,129
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
505,129 -> 505,131 -> 504,131 -> 504,135 -> 517,135 -> 517,131 -> 509,131 -> 509,129
484,82 -> 488,82
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
494,61 -> 494,63 -> 490,63 -> 490,70 -> 499,70 -> 499,63 -> 498,63 -> 498,61
500,138 -> 500,142 -> 498,142 -> 498,146 -> 513,146 -> 513,142 -> 506,142 -> 506,138
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
462,58 -> 467,58
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
498,13 -> 498,16 -> 496,16 -> 496,20 -> 509,20 -> 509,16 -> 502,16 -> 502,13
487,113 -> 492,113
487,73 -> 491,73
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
505,129 -> 505,131 -> 504,131 -> 504,135 -> 517,135 -> 517,131 -> 509,131 -> 509,129
469,103 -> 469,104 -> 484,104 -> 484,103
487,79 -> 491,79
479,29 -> 489,29 -> 489,28
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
500,138 -> 500,142 -> 498,142 -> 498,146 -> 513,146 -> 513,142 -> 506,142 -> 506,138
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
487,85 -> 491,85
475,49 -> 480,49
479,109 -> 484,109
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
469,103 -> 469,104 -> 484,104 -> 484,103
494,61 -> 494,63 -> 490,63 -> 490,70 -> 499,70 -> 499,63 -> 498,63 -> 498,61
494,61 -> 494,63 -> 490,63 -> 490,70 -> 499,70 -> 499,63 -> 498,63 -> 498,61
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
485,42 -> 485,41 -> 485,42 -> 487,42 -> 487,38 -> 487,42 -> 489,42 -> 489,32 -> 489,42 -> 491,42 -> 491,33 -> 491,42 -> 493,42 -> 493,34 -> 493,42
498,13 -> 498,16 -> 496,16 -> 496,20 -> 509,20 -> 509,16 -> 502,16 -> 502,13
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
468,98 -> 468,96 -> 468,98 -> 470,98 -> 470,90 -> 470,98 -> 472,98 -> 472,90 -> 472,98 -> 474,98 -> 474,92 -> 474,98 -> 476,98 -> 476,97 -> 476,98 -> 478,98 -> 478,97 -> 478,98
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162
494,61 -> 494,63 -> 490,63 -> 490,70 -> 499,70 -> 499,63 -> 498,63 -> 498,61
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
478,47 -> 483,47
500,138 -> 500,142 -> 498,142 -> 498,146 -> 513,146 -> 513,142 -> 506,142 -> 506,138
493,126 -> 493,123 -> 493,126 -> 495,126 -> 495,117 -> 495,126 -> 497,126 -> 497,123 -> 497,126 -> 499,126 -> 499,122 -> 499,126 -> 501,126 -> 501,119 -> 501,126 -> 503,126 -> 503,125 -> 503,126 -> 505,126 -> 505,122 -> 505,126
478,162 -> 478,154 -> 478,162 -> 480,162 -> 480,159 -> 480,162 -> 482,162 -> 482,156 -> 482,162 -> 484,162 -> 484,161 -> 484,162 -> 486,162 -> 486,155 -> 486,162 -> 488,162 -> 488,159 -> 488,162 -> 490,162 -> 490,158 -> 490,162 -> 492,162 -> 492,159 -> 492,162 -> 494,162 -> 494,158 -> 494,162 -> 496,162 -> 496,153 -> 496,162"""