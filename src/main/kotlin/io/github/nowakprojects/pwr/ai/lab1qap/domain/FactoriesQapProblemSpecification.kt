package io.github.nowakprojects.pwr.ai.lab1qap.domain

import io.github.nowakprojects.pwr.ai.lab1qap.infrastructure.IntMatrix

class FactoriesQapProblemSpecification(
        val factoriesAmount: Int,
        val flowMatrix: IntMatrix,
        val distanceMatrix: IntMatrix
) {
    constructor(factoriesCount: Int, flowMatrix: Array<Array<Int>>, distanceMatrix: Array<Array<Int>>)
            : this(factoriesCount, IntMatrix(flowMatrix), IntMatrix(distanceMatrix))

    fun getPossibleFactories() = (1..factoriesAmount).map { it }.toTypedArray()
}