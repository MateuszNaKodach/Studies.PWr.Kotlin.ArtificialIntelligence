package io.github.nowakprojects.pwr.ai.lab1.domain

class FactoriesQapProblemSpecification(
        val factoriesCount: Int,
        val flowMatrix: IntMatrix,
        val distanceMatrix: IntMatrix
) {
    constructor(factoriesCount: Int, flowMatrix: Array<Array<Int>>, distanceMatrix: Array<Array<Int>>)
            : this(factoriesCount, IntMatrix(flowMatrix), IntMatrix(distanceMatrix))

    fun getPossibleFactories() = (0..factoriesCount).map { it }.toTypedArray()
}