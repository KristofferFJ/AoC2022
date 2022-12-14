package utils


class ListUtils {
    companion object {
        fun <T> List<T>.partitionListsInGroupsOfSize(size: Int): List<List<T>> {
            return this.withIndex().groupBy {
                it.index / size
            }.map { it.value.map { it.value } }
        }

        fun <T> List<T>.partitionByElement(element: T): List<List<T>> {
            val lists = mutableListOf<MutableList<T>>()
            var currentList = mutableListOf<T>()
            for(item in this) {
                if(item == element) {
                    lists.add(currentList)
                    currentList = mutableListOf()
                } else {
                    currentList.add(item)
                }
            }
            lists.add(currentList)
            return lists
        }

        fun <T> List<List<T>>.transpose(): MutableList<MutableList<T>> {
            val ret: MutableList<MutableList<T>> = mutableListOf()
            val length = this[0].size
            for (i in 0 until length) {
                val col: MutableList<T> = mutableListOf()
                for (row in this) {
                    col.add(row[i])
                }
                ret.add(col)
            }
            return ret
        }

        fun <T> MutableList<T>.removeSlice(amount: Int): MutableList<T> {
            val removedSlice = mutableListOf<T>()
            for (i in 1..amount) {
                removedSlice.add(this.last())
                this.removeLast()
            }
            return removedSlice
        }

        fun List<Long>.prod(): Long {
            var prod: Long = 1
            this.forEach { prod *= it }
            return prod
        }

        fun List<Int>.prod(): Int {
            var prod = 1
            this.forEach { prod *= it }
            return prod
        }

        fun List<List<String>>.printGrid(): String {
            return this.joinToString("\n") { it.joinToString("") }
        }
    }
}