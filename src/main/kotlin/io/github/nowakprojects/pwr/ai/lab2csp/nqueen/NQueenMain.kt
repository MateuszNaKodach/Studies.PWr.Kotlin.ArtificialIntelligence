package io.github.nowakprojects.pwr.ai.lab2csp.nqueen

fun main(args: Array<String>) {
    val nQueens = listOf(4, 5, 6, 7, 8, 9, 10)
    nQueens.forEach {
        val problem = NQueenSolver(it)
        println("BACK TRACKING:")
        val backTrackingSolution = problem.solveWith(NQueenSolver.Algorithm.BACK_TRACKING, NQueenSolver.PERMUTATION.FROM_MIDDLE)
        println(backTrackingSolution.toString())
        //backTrackingSolution.printFirstSolution()
       // println()
       // println("FORWARD CHECKING:")
      //  val forwardCheckingSolution = problem.solveWith(NQueenSolver.Algorithm.FORWARD_CHECKING, NQueenSolver.PERMUTATION.FROM_MIDDLE)
      //  println(forwardCheckingSolution.toString())
        //forwardCheckingSolution.printFirstSolution()
    }


}