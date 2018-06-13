package io.github.nowakprojects.pwr.ai.lab3minmax

import io.github.nowakprojects.pwr.ai.lab3minmax.game.*
import io.github.nowakprojects.pwr.ai.lab3minmax.game.costCount.ClassicCostCount
import io.github.nowakprojects.pwr.ai.lab3minmax.game.costCount.MaxCurrentCostCount
import io.github.nowakprojects.pwr.ai.lab3minmax.game.costCount.MinOppositeCostCount
import io.github.nowakprojects.pwr.ai.lab3minmax.game.emptyFieldIterator.BasicEmptyFieldIterator
import io.github.nowakprojects.pwr.ai.lab3minmax.game.emptyFieldIterator.CenterToOutsideFieldIterator
import io.github.nowakprojects.pwr.ai.lab3minmax.game.emptyFieldIterator.OutsideToCenterFieldIterator
import io.github.nowakprojects.pwr.ai.lab3minmax.game.moveGenerator.SimulationAlfaBeta
import io.github.nowakprojects.pwr.ai.lab3minmax.game.moveGenerator.SimulationMinMax
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.Alert.AlertType
import javafx.scene.input.MouseEvent
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.RowConstraints
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle


class Controller(private val strategoGame: Stratego) : IPlayerInterface {

    @FXML
    private lateinit var boardGridPane: GridPane

    @FXML
    private lateinit var newGameButton: Button

    @FXML
    private lateinit var player1NameLabel: Label

    @FXML
    private lateinit var player2NameLabel: Label

    @FXML
    private lateinit var player1PointsLabel: Label

    @FXML
    private lateinit var player2PointsLabel: Label

    @FXML
    private lateinit var player1TypeChoiceBox: ChoiceBox<String>

    @FXML
    private lateinit var player2TypeChoiceBox: ChoiceBox<String>

    @FXML
    private lateinit var player1SimulationChoiceBox: ChoiceBox<String>

    @FXML
    private lateinit var player2SimulationChoiceBox: ChoiceBox<String>

    @FXML
    private lateinit var player1IteratorChoiceBox: ChoiceBox<String>

    @FXML
    private lateinit var player2IteratorChoiceBox: ChoiceBox<String>

    @FXML
    private lateinit var player1CostChoiceBox: ChoiceBox<String>

    @FXML
    private lateinit var player2CostChoiceBox: ChoiceBox<String>

    @FXML
    private lateinit var boardSizeTextField: TextField

    @FXML
    private lateinit var deepPlayer1TextField: TextField

    @FXML
    private lateinit var deepPlayer2TextField: TextField

    private lateinit var player1Setting: PlayerSetting

    private lateinit var player2Setting: PlayerSetting

    private var fields = mutableListOf<Rectangle>()

    private var size = 0

    private var clickable = true

    private var thread: Thread? = null

    private var timeStartMove = 0L

    var time1 = 0L
    var time2 = 0L

    private fun initialiseBoard() {

        fields = mutableListOf()
        val factor = 660 / size.toDouble()

        for (i in 0 until size) {
            boardGridPane.addRow(i)
            boardGridPane.addColumn(i)
            boardGridPane.rowConstraints.add(RowConstraints(factor))
            boardGridPane.columnConstraints.add(ColumnConstraints(factor))
            for (j in 0 until size) {

                val r = Rectangle()
                r.width = factor
                r.height = factor
                if ((i + j) % 2 == 0) r.fill = Color.web("#FFECB3") else {
                    r.fill = Color.web("#FFE0B2")
                }
                r.onMouseClicked = EventHandler<MouseEvent> { click(i, j) }
                fields.add(r)
                boardGridPane.add(r, i, j)
            }
        }
        boardGridPane.isGridLinesVisible = false
    }

    private fun refreshPoints() {

        fun getPointsString(points: Int) = "$points pkt"

        val points = strategoGame.getPoints()
        player1PointsLabel.text = getPointsString(points.first)
        player2PointsLabel.text = getPointsString(points.second)
    }

    private fun click(i: Int, j: Int) {
        if (clickable) {
            put(i, j)
        }
    }

    private fun put(i: Int, j: Int) {
        val currentPlayer = strategoGame.currentPlayer()
        if (currentPlayer == PlayerEnum.PLAYER_1)
            time1 += System.currentTimeMillis() - timeStartMove
        else
            time2 += System.currentTimeMillis() - timeStartMove
        timeStartMove = System.currentTimeMillis()
        if (strategoGame.put(i, j)) {
            fields[i * size + j].fill =
                    when (currentPlayer) {
                        PlayerEnum.PLAYER_1 -> Color.web("#009688")
                        PlayerEnum.PLAYER_2 -> Color.web("#F44336")
                    }
            refreshPoints()
            supportEndOfGame()
            if (!strategoGame.isEndOfGame()) {
                showCurrentPlayerMove()
            }
        }
    }

    private fun supportEndOfGame() {
        if (strategoGame.isEndOfGame()) {
            println("player1: $time1  player2: $time2")
            val points = strategoGame.getPoints()
            val alert = Alert(AlertType.INFORMATION)
            alert.title = "Gra zakończona"
            alert.headerText = null
            alert.contentText =
                    if (points.first == points.second)
                        "Gra zakończyła się remisem"
                    else "Wygrał Player ${if (points.first > points.second) 1 else 2}"
            alert.showAndWait()
        }
    }

    private fun showCurrentPlayerMove() {

        if (strategoGame.currentPlayer() == PlayerEnum.PLAYER_1) {
            player1NameLabel.textFill = Color.BLUE
            player2NameLabel.textFill = Color.BLACK
            val maker = getComputerPlayerFromSetting(player1Setting)
            thread = maker?.makeMove()
            clickable = maker == null
        } else {
            player1NameLabel.textFill = Color.BLACK
            player2NameLabel.textFill = Color.BLUE
            val maker = getComputerPlayerFromSetting(player2Setting)
            thread = maker?.makeMove()
            clickable = maker == null
        }
    }

    private fun getFieldIterator(id: Int) =
            when (id) {
                1 -> OutsideToCenterFieldIterator()
                2 -> CenterToOutsideFieldIterator()
                else -> BasicEmptyFieldIterator()
            }

    private fun getCostCount(id: Int) =
            when (id) {
                1 -> MaxCurrentCostCount()
                2 -> MinOppositeCostCount()
                else -> ClassicCostCount()
            }

    private fun getComputerPlayerFromSetting(playerSetting: PlayerSetting) =
            if (playerSetting.isComputerPlayer)
                ComputerPlayer(
                        if (playerSetting.simulation == 0) SimulationMinMax(strategoGame, playerSetting.playerEnum,
                                playerSetting.deep, getFieldIterator(playerSetting.emptyFieldGenerator),
                                getCostCount(playerSetting.costCount))
                        else SimulationAlfaBeta(strategoGame, playerSetting.playerEnum,
                                playerSetting.deep, getFieldIterator(playerSetting.emptyFieldGenerator),
                                getCostCount(playerSetting.costCount)), this)
            else null

    override fun nextMove(move: Pair<Int, Int>) {
        put(move.first, move.second)
    }

    private fun getSettingFromSelectedIndexes(isComputerplayingIndex: Int, playerEnumIndex: Int,
                                              fieldGeneratorIndex: Int, contCountIndex: Int,
                                              playerEnum: PlayerEnum, deep: Int) =
            PlayerSetting(isComputerplayingIndex == 1, playerEnum, playerEnumIndex, deep,
                    fieldGeneratorIndex, contCountIndex)

    private fun initPlayersSettings() {
        player1Setting = getSettingFromSelectedIndexes(player1TypeChoiceBox.selectionModel.selectedIndex,
                player1SimulationChoiceBox.selectionModel.selectedIndex,
                player1IteratorChoiceBox.selectionModel.selectedIndex,
                player1CostChoiceBox.selectionModel.selectedIndex, PlayerEnum.PLAYER_1,
                deepPlayer1TextField.text.toIntOrNull()?:3)

        player2Setting = getSettingFromSelectedIndexes(player2TypeChoiceBox.selectionModel.selectedIndex,
                player2SimulationChoiceBox.selectionModel.selectedIndex,
                player2IteratorChoiceBox.selectionModel.selectedIndex,
                player2CostChoiceBox.selectionModel.selectedIndex, PlayerEnum.PLAYER_2,
                deepPlayer2TextField.text.toIntOrNull()?:3)
    }

    private fun prepareNewGame() {
        time1 = 0
        time2 = 0
        thread?.interrupt()
        val newSize = boardSizeTextField.text.toIntOrNull()
        if (newSize == null || newSize == 0) {
            val alert = Alert(AlertType.INFORMATION)
            alert.title = "Błąd"
            alert.headerText = null
            alert.contentText = "Niepoprawna wielkość planszy"
            alert.showAndWait()
        } else {
            size = newSize
            initPlayersSettings()
            boardGridPane.children.clear()
            boardGridPane.columnConstraints.clear()
            boardGridPane.rowConstraints.clear()
            initialiseBoard()
            strategoGame.newGame(size)
            refreshPoints()
            showCurrentPlayerMove()
            timeStartMove = System.currentTimeMillis()
        }
    }

    private fun setOnlyNumberInTextField(textField: TextField) {
        textField.textProperty().addListener { _, oldValue, newValue ->
            if (!newValue.matches(Regex("\\d*")) || newValue.length > 3) {
                boardSizeTextField.text = oldValue
            }
        }
    }

    @FXML
    fun initialize() {
        boardSizeTextField.text = 8.toString()
        setOnlyNumberInTextField(boardSizeTextField)
        setOnlyNumberInTextField(deepPlayer1TextField)
        setOnlyNumberInTextField(deepPlayer2TextField)
        newGameButton.onMouseClicked = EventHandler<MouseEvent> { prepareNewGame() }
        prepareNewGame()
    }
}