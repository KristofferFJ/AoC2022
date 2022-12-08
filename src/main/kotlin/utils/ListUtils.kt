package utils


class ListUtils {
    companion object {
        fun <T> List<T>.partitionListsInGroupsOfSize(size: Int): List<List<T>> {
            return this.withIndex().groupBy {
                it.index / size
            }.map { it.value.map { it.value } }
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
    }
}