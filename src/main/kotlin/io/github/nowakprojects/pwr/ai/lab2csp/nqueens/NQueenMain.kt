package io.github.nowakprojects.pwr.ai.lab2csp.nqueens

import io.github.nowakprojects.pwr.ai.lab2csp.nqueens.domain.NQueensProblemGenerator

fun main(args: Array<String>) {
    val N = 8
    NQueensProblemGenerator(N).generate()
}