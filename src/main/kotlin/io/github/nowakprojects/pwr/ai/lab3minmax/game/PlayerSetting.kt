package io.github.nowakprojects.pwr.ai.lab3minmax.game

/**
 * @param isComputerPlayer boolean that player is computer
 * @param playerEnum the enum of player
 * @param simulation the type of simulation
 * @param deep deep of game tree
 * @param emptyFieldGenerator the generator of empty field interator
 * @param costCount the iterator of next
 */
data class PlayerSetting(
        val isComputerPlayer: Boolean,
        val playerEnum: PlayerEnum,
        val simulation: Int,
        val deep: Int,
        val emptyFieldGenerator: Int,
        val costCount: Int
)