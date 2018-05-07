package io.github.nowakprojects.pwr.ai.lab2csp.nqueen

fun main(args: Array<String>) {
    val problem = NQueenProblem(4)

    problem.solveWithBackTracking()
    println("BACK TRACKING SOLUTIONS: ${problem.solutions.size}")
    problem.solutions.forEach { problem.prettyPrintChessboard(it); println() }
    println("BACK TRACKING SOLUTIONS --- ${problem.solutions.size}")

    problem.prettyPrintChessboard()

    problem.solveWithForwardChecking()
    println("FORWARD CHECKING SOLUTIONS: ${problem.solutions.size}")
    problem.solutions.forEach { problem.prettyPrintChessboard(it); println() }
    println("FORWARD CHECKING SOLUTIONS --- ${problem.solutions.size}")


}