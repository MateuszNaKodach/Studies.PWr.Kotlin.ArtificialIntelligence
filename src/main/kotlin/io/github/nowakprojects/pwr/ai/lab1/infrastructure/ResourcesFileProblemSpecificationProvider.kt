package io.github.nowakprojects.pwr.ai.lab1.infrastructure

import io.github.nowakprojects.pwr.ai.lab1.domain.FactoriesQapProblemSpecification
import java.util.*
import java.util.Objects.isNull


class ResourcesFileProblemSpecificationProvider(
        private val resourcePath: String
) : ProblemSpecificationProvider {

    override fun provide(): FactoriesQapProblemSpecification {
        val resourceAsStream = javaClass.getResourceAsStream(resourcePath)
        if (isNull(resourceAsStream)) {
            throw ProblemSpecificationNotFound("Resource file $resourcePath not exists!");
        }
        val scanner = Scanner(javaClass.getResourceAsStream(resourcePath))
        val factoriesAmount = scanner.nextInt()
        val flowMatrix = loadFlowMatrix(factoriesAmount, scanner)
        val distanceMatrix = loadDistanceMatrix(factoriesAmount, scanner)
        return FactoriesQapProblemSpecification(
                factoriesAmount,
                flowMatrix.map { it.toTypedArray() }.toTypedArray(),
                distanceMatrix.map { it.toTypedArray() }.toTypedArray()
        )
    }

    //TODO: Consider which approach use in whole program Array<IntArray> or Array<Array<Int>>
    private fun loadDistanceMatrix(factoriesAmount: Int, scanner: Scanner): Array<IntArray> {
        val distanceMatrix = Array(factoriesAmount) { IntArray(factoriesAmount) }
        (0 until factoriesAmount).forEach { i ->
            (0 until factoriesAmount).forEach { j -> distanceMatrix[i][j] = scanner.nextInt() }
        }
        return distanceMatrix
    }

    private fun loadFlowMatrix(factoriesAmount: Int, scanner: Scanner): Array<IntArray> {
        val flowMatrix = Array(factoriesAmount) { IntArray(factoriesAmount) }
        (0 until factoriesAmount).forEach { i ->
            (0 until factoriesAmount).forEach { j -> flowMatrix[i][j] = scanner.nextInt() }
        }
        return flowMatrix
    }
}

class ProblemSpecificationNotFound(message: String, cause: Throwable? = null) : RuntimeException(message, cause)