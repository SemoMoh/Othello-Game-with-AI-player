package com.cse.ai.othellogame;

import com.cse.ai.othellogame.gui.mainmenu.MainMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    public static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(new MainMenu(), 1920, 1080);
        HelloApplication.scene = scene;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void endGame(){
        stage.close();
    }

    public static void main(String[] args) {
        System.setProperty("prism.allowhidpi", "false");
        launch();
    }
}