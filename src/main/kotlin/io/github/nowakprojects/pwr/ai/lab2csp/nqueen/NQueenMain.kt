package io.github.nowakprojects.pwr.ai.lab2csp.nqueen

fun main(args: Array<String>) {
    val problem = NQueenProblem(8)
    problem.solveWithBackTracking()
    problem.solutions.forEach { problem.prettyPrintChessboard(it); println() }
}