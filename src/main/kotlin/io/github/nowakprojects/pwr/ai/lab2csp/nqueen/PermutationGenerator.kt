package io.github.nowakprojects.pwr.ai.lab2csp.nqueen


fun IntRange.permutationFromMiddle(): List<Int> {
    val list = this.toList()
    assert(list.size >= 3, {"Range to permutation needs to have at least 3 elements!"})
    val result: MutableList<Int> = mutableListOf()
    val middleIndex = this.toList().size / 2
    result.add(list[middleIndex])
    (1..middleIndex).forEach {
        if (middleIndex - it < list.size) {
            result.add(list[middleIndex - it])
        }
        if (middleIndex + it < list.size-1) {
            result.add(list[middleIndex + it])
        }
    }
    result.add(list.last())
    return result
}