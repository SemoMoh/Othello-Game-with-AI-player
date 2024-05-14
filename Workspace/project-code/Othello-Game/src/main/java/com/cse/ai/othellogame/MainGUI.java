package com.cse.ai.othellogame;

import com.cse.ai.othellogame.gui.mainmenu.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUI extends Application {
    public static Stage stage;
    public static Scene scene;

    public static void mainMenu() {
        scene.setRoot(new MainMenu());
    }

    @Override
    public void start(Stage stage) throws IOException {
        MainGUI.stage = stage;
        MainGUI.scene = new Scene(new MainMenu(), 1920, 1080);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                event.consume(); // Consume the event to prevent further processing
            }
        });
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setTitle("Hello!");
        stage.show();
    }

    public static void endGame() {
        stage.close();
    }

    public static void main(String[] args) {
        System.setProperty("prism.allowhidpi", "false");
        launch();
    }
}