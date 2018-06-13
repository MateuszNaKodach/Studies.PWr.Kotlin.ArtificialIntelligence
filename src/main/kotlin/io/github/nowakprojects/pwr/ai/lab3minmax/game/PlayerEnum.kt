package io.github.nowakprojects.pwr.ai.lab3minmax.game

/**
 * The enum of player in game
 */
enum class PlayerEnum {
    PLAYER_1, PLAYER_2;

    /**
     * Method to get the opposite player
     * @return opposite player
     */
    fun getOpposite() = if (this == PLAYER_1)
        PLAYER_2
    else PLAYER_1
}
