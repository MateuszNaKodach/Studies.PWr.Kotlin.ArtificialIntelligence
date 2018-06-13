package io.github.nowakprojects.pwr.ai.lab3minmax.game

import io.github.nowakprojects.pwr.ai.lab3minmax.game.moveGenerator.IMoveGenerator
import javafx.application.Platform

/**
 * the computer player executor
 * @param moveGenerator the moveGenerator
 * @param playerInterface the playerInterface
 */
class ComputerPlayer(private val moveGenerator: IMoveGenerator, private val playerInterface: IPlayerInterface) {

    /**
     * Method to execute next move
     */
    fun makeMove() =
        Thread {
            val p = moveGenerator.nextMove()
            Platform.runLater {
                playerInterface.nextMove(p)
            }
        }.apply { start() }
}