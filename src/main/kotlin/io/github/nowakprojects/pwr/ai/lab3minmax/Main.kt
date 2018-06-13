
package io.github.nowakprojects.pwr.ai.lab3minmax

import io.github.nowakprojects.pwr.ai.lab3minmax.game.Stratego
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader()
        root.location = javaClass.getResource("/main.fxml")
        root.setController(Controller(Stratego()))
        primaryStage.title = "Gra stratego"
        primaryStage.scene = Scene(root.load(), 1100.0, 700.0)
        primaryStage.isResizable=false
        primaryStage.show()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
