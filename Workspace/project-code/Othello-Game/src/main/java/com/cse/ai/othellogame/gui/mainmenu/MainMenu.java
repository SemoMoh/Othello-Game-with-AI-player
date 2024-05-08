package com.cse.ai.othellogame.gui.mainmenu;

import com.cse.ai.othellogame.gui.gamescreen.GameScreen;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.cse.ai.othellogame.HelloApplication;
import com.cse.ai.othellogame.HelloController;
import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.backend.player.AIPlayer;
import com.cse.ai.othellogame.backend.player.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;



public class MainMenu extends Pane implements Initializable {


    public static MainMenu mainMenu;

    @FXML
    public Pane root;

    public MainMenu(){

        mainMenu = this;

        //load fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "main-menu-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //root.getChildren().add(this);

    }
}
