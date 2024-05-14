package com.cse.ai.othellogame;

import com.cse.ai.othellogame.gui.mainmenu.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;


public class MainGUI extends Application {
    public static Stage stage;
    public static Scene scene;

    /**
     * This method is used to change the scene to the main menu.
     */
    public static void mainMenu() {
        scene.setRoot(new MainMenu());
    }

    /**
     * This method is used to close the game application.
     */
    public static void endGame() {
        stage.close();
    }

    public static void main(String[] args) {
        // to stop the windows from scaling the game
        System.setProperty("prism.allowhidpi", "false");
        // start the game
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        MainGUI.stage = stage;
        MainGUI.scene = new Scene(new MainMenu(), 1920, 1080);

        // set to full screen
        stage.setFullScreen(true);
        // hide full screen hint and stop the game from exiting full screen
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                event.consume(); // Consume the event to prevent further processing
            }
        });
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        // set the scene and show the stage
        stage.setScene(scene);
        stage.setTitle("Hello!");
        stage.show();
    }
}