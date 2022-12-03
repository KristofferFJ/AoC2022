package utils

class LetterUtil {
    companion object {
        private val LETTERS = listOf(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        )

        fun getLetterOrdinal(char: Char): Int {
            if (char.isUpperCase()) {
                return LETTERS.indexOf(char.lowercaseChar()) + 27
            }
            return LETTERS.indexOf(char) + 1
        }
    }
}