package utils

class ListUtils {
    companion object {
        fun <T> partitionListsInGroupsOfSize(list: List<T>, size: Int): List<List<T>> {
            return list.withIndex().groupBy {
                it.index / size
            }.map { it.value.map { it.value } }
        }
    }
}