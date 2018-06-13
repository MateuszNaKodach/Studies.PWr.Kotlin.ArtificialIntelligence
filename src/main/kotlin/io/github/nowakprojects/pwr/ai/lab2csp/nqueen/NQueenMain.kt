package io.github.nowakprojects.pwr.ai.lab2csp.nqueen

fun main(args: Array<String>) {
    val nQueens = listOf(8)
    nQueens.forEach {
        val problem = NQueenSolver(it)
        println("BACK TRACKING:")
        val backTrackingSolution = problem.solveWith(NQueenSolver.Algorithm.BACK_TRACKING, NQueenSolver.PERMUTATION.ORDERED)
        println(backTrackingSolution.toString())
        //backTrackingSolution.printFirstSolution()
       // println()
        println("FORWARD CHECKING:")
        val forwardCheckingSolution = problem.solveWith(NQueenSolver.Algorithm.FORWARD_CHECKING, NQueenSolver.PERMUTATION.ORDERED)
        println(forwardCheckingSolution.toString())
        //forwardCheckingSolution.printFirstSolution()
    }


}