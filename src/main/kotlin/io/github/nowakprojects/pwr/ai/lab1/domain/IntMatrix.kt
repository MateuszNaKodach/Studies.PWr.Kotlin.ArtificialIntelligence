package io.github.nowakprojects.pwr.ai.lab1.domain

class IntMatrix(
        val content: Array<IntArray>
) {
    fun get(x: Int, y: Int) = content[x][y]
    fun get(x: Int) = content[x]
}