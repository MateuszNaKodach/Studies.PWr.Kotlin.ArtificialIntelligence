package io.github.nowakprojects.pwr.ai.lab2csp.nHetman

/**
 * Method to test the nQueens solve algorithm
 */
fun main(args: Array<String>) {

    (4..8).forEach {
        println("Size $it")
        val chessBoard = Chessboard.create(it)
        print("Solutions count: ")
        println(BacktrackMethod(chessBoard).findSolution().size)
        println("------------\n")
    }
}