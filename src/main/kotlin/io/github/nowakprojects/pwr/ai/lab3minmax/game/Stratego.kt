package io.github.nowakprojects.pwr.ai.lab3minmax.game

import java.util.*


class Stratego {

    /**
     * The board side size
     */
    private var boardSize = 0

    /**
     * Current player enum
     */
    private var currentPlayer = PlayerEnum.PLAYER_1

    /**
     * Current points
     */
    private var points = Pair(0, 0)

    /**
     * The array to get quickly index of pointMatrix array
     */
    private lateinit var diagonalIndexes: Array<Array<Pair<Int, Int>>>

    /**
     * The representation of fields on board
     */
    lateinit var bordMatrix: Array<Array<FieldType>>

    /**
     * The matrix to quick calculate the points
     */
    private lateinit var pointMatrix: Array<Array<Int>>

    /**
     * Stack of moves
     */
    private lateinit var stack: Stack<Pair<Pair<Int, Int>, Pair<Int, Int>>>

    /**
     * Count of busy fields
     */
    var filledFields = 0

    /**
     * Method to get the result of fame
     * @return the result
     */
    fun getPoints(): Pair<Int, Int> = points

    /**
     * The method to make move on field
     * @param i first dimension od move location
     * @param j second dimension od move location
     */
    fun put(i: Int, j: Int) =
            if (bordMatrix[i][j] == FieldType.EMPTY) {
                putOnStack(i, j)
                if (currentPlayer == PlayerEnum.PLAYER_1) {
                    bordMatrix[i][j] = FieldType.PLAYER_1
                    currentPlayer = PlayerEnum.PLAYER_2
                    points = Pair(points.first + updatePointMatrix(i, j), points.second)
                } else {
                    bordMatrix[i][j] = FieldType.PLAYER_2
                    currentPlayer = PlayerEnum.PLAYER_1
                    points = Pair(points.first, points.second + updatePointMatrix(i, j))
                }
                filledFields++
                true
            } else false

    /**
     * Teh method to undo the last move
     */
    fun takeOffLast() {

        if (stack.isEmpty()) {
            throw Error("Empty stack while take off last position")
        }

        val pair = stack.pop()
        val i = pair.first.first
        val j = pair.first.second
        filledFields--
        bordMatrix[i][j] = FieldType.EMPTY
        points = pair.second
        currentPlayer = if (currentPlayer == PlayerEnum.PLAYER_1) {
            PlayerEnum.PLAYER_2
        } else {
            PlayerEnum.PLAYER_1
        }

        pointMatrix[0][i]++
        pointMatrix[1][j]++
        pointMatrix[2][diagonalIndexes[i][j].first]++
        pointMatrix[3][diagonalIndexes[i][j].second]++
    }


    /**
     * Method to put move on the stack
     */
    private fun putOnStack(i: Int, j: Int) {
        stack.add(Pair(Pair(i, j), points))
    }

    /**
     * Method to update points
     * @param i first dimension of last move
     * @param j second dimension of last move
     */
    private fun updatePointMatrix(i: Int, j: Int): Int {
        var p = 0
        pointMatrix[0][i]--
        if (pointMatrix[0][i] == 0)
            p += boardSize

        pointMatrix[1][j]--
        if (pointMatrix[1][j] == 0)
            p += boardSize

        val d1 = diagonalIndexes[i][j].first
        pointMatrix[2][d1]--
        if (pointMatrix[2][d1] == 0) {
            val s1 = diagonalSize(d1)
            if (s1 != 1)
                p += s1
        }

        val d2 = diagonalIndexes[i][j].second
        pointMatrix[3][d2]--
        if (pointMatrix[3][d2] == 0) {
            val s1 = diagonalSize(d2)
            if (s1 != 1)
                p += s1
        }

        return p
    }

    /**
     * Method to check that is end of game
     * @return that is end of game
     */
    fun isEndOfGame() = filledFields == (boardSize * boardSize)

    /**
     * Method to return the current player
     */
    fun currentPlayer() = currentPlayer

    /**
     * Method to get the diagonal size
     * @param ind the index of diagonal (see pointMatrix)
     * @return size of matrix
     */
    private fun diagonalSize(ind: Int) = if (ind < boardSize) ind + 1 else boardSize * 2 - ind - 1

    /**
     * Method to init the pointMatrix
     */
    private fun initPointsMatrix() {
        pointMatrix = Array(4, {
            when (it) {
                0, 1 -> Array(boardSize, { boardSize })
                else -> Array(boardSize * 2 - 1, { diagonalSize(it) })
            }
        })
    }

    /**
     * Method to init the diagonalIndexes
     */
    private fun initDiagonalIndexes() {
        diagonalIndexes = Array(boardSize, { d1 ->
            Array(boardSize, { d2 -> Pair(getFirstIndexDiagonal(d1, d2), getSecondIndexDiagonal(d1, d2)) })
        })
    }

    /**
     * Method to get the first diagonal index
     * @param i first dimension of move
     * @param j second dimension of move
     * @return the index of first diagonal
     */
    private fun getFirstIndexDiagonal(i: Int, j: Int) = i - j + boardSize - 1

    /**
     * Method to get the second diagonal index
     * @param i first dimension of move
     * @param j second dimension of move
     * @return the index of second diagonal
     */
    private fun getSecondIndexDiagonal(i: Int, j: Int) = i - (boardSize - j - 1) + boardSize - 1

    /**
     * Start new game
     * @param size size of game
     */
    fun newGame(size: Int) {
        boardSize = size
        bordMatrix = Array(size, {
            Array(size, { FieldType.EMPTY })
        })
        points = Pair(0, 0)
        filledFields = 0
        currentPlayer = PlayerEnum.PLAYER_1
        initPointsMatrix()
        initDiagonalIndexes()
        stack = Stack()
    }

    /**
     * Enum of field type
     */
    enum class FieldType {
        EMPTY, PLAYER_1, PLAYER_2
    }

}