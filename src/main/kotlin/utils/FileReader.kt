package utils

import java.io.File

class FileReader {
    companion object {
        fun readAsLines(day: String): List<String> {
            return File("src/main/kotlin/$day/input.txt").readLines()
        }

        fun readAsText(day: String): String {
            return File("src/main/kotlin/$day/input.txt").readText()
        }
    }
}