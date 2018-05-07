package io.github.nowakprojects.pwr.ai.lab2csp.queens.domain

class Backtracking(val n: Int) {

    val chessBoard = Chessboard.empty(n)

    val selectedPoints: MutableList<Point> = ArrayList<Point>();

    fun getAllPossibleValues(): Set<Point> {
        val result = emptySet<Point>().toMutableSet()
        (0 until n).forEach { x ->
            (0 until n).forEach { y -> result.add(Point(x, y)) }
        }
        return result;
    }

    /*
    fun findSolutionForQueenInRow(yRow: Int, xColumn: Int):Chessboard {
        var currentRowY = yRow
        var currentColumnX = xColumn
        while (currentRowY<n) {
            if (chessBoard.isNoQueenAttackedByRowOrColumnOnPoint(currentColumnX, currentRowY)){
                chessBoard.placeChessman(Chessman.QUEEN,currentColumnX, currentRowY);
                selectedPoints.add(Point(currentColumnX, currentRowY))
                currentColumnX++
            }else{
                currentRowY++
            }
        }
        if(selectedPoints.size == n){
            return chessBoard.clone()
        }else{
            val lastPoint = selectedPoints.last()
            chessBoard.placeChessman(Chessman.NONE,lastPoint.x,lastPoint.y)
            selectedPoints.removeAt(selectedPoints.size)
            return findSolutionForQueenInRow(lastPoint.x+1,lastPoint.y)
        }
    }*/

    fun findSolutionFor(xColumn: Int, yRow: Int, iteration: Int = 1): Chessboard {
        if (chessBoard.getPointsWithQueens().size == n) {
            return chessBoard.clone();
        }
        if (chessBoard.isNoQueenAttackedOnPoint(xColumn, yRow)) {
            chessBoard.placeQueen(xColumn, yRow)
            return findSolutionFor(xColumn + 1, yRow + 1)
        } else {
            if (yRow == n - 1 && xColumn == n - 1) {
                throw RuntimeException("No solution!")
            }
            if (yRow == n - 1) {
                return findSolutionFor(xColumn - 1, yRow)
            }
            return findSolutionFor(xColumn + 1, yRow)
        }
    }
}