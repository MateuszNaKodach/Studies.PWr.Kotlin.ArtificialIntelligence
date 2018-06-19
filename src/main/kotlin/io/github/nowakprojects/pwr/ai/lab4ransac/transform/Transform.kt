package transform

import heuristics.Heuristics
import Point
import org.ejml.simple.SimpleMatrix

abstract class Transform(protected val heuristics: Heuristics) {

    protected abstract val pointsCount: Int

    abstract fun model(pairs: List<Pair<Point, Point>>): SimpleMatrix?

    protected fun model(firstMatrix: SimpleMatrix, secondMatrix: SimpleMatrix): SimpleMatrix? =
            try {
                val resultOfMultiplication = firstMatrix.invert().mult(secondMatrix)
                model(resultOfMultiplication)
            } catch (e: Exception) {
                null
            }

    protected abstract fun model(vector: SimpleMatrix): SimpleMatrix
}
