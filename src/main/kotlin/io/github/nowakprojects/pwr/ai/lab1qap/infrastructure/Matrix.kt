package io.github.nowakprojects.pwr.ai.lab1qap.infrastructure

import java.lang.StringBuilder

abstract class Matrix<T>(
        val content: Array<Array<T>>
) {
    fun get(x: Int, y: Int) = content[x][y]
    fun get(x: Int) = content[x]

    override fun toString(): String {
        return StringBuilder().apply {
            content.map { row -> row.toList().toString() }.forEach { this.append(it).append("\n") }
        }.toString()
    }

    fun prettyPrint() {
        print(toString())
    }
}