package com.cse.ai.othellogame;

import com.cse.ai.othellogame.gui.mainmenu.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    public static Scene scene;

    public static void mainMenu() {
        scene.setRoot(new MainMenu());
    }

    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
        HelloApplication.scene = new Scene(new MainMenu(), 1920, 1080);
        stage.setScene(scene);
        stage.setFullScreen(true);
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