package utils

class ListUtils {
    companion object {
        fun <T> List<T>.partitionListsInGroupsOfSize(size: Int): List<List<T>> {
            return this.withIndex().groupBy {
                it.index / size
            }.map { it.value.map { it.value } }
        }
    }
}