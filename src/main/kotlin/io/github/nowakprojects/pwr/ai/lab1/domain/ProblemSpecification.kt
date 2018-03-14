package io.github.nowakprojects.pwr.ai.lab1.domain

class ProblemSpecification(
        val factoriesCount: Int,
        val flowMatrix: IntMatrix,
        val distanceMatrix: IntMatrix
) {
    constructor(factoriesCount: Int, flowMatrix: Array<IntArray>, distanceMatrix: Array<IntArray>)
            : this(factoriesCount, IntMatrix(flowMatrix), IntMatrix(distanceMatrix))
}