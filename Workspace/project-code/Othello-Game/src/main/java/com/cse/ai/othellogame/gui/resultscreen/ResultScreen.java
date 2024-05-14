package com.cse.ai.othellogame.gui.resultscreen;

import com.cse.ai.othellogame.backend.game.Board;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultScreen extends Pane implements Initializable {
    private final Board board;

    public ResultScreen(Board board, String playerBlackName, String playerWhiteName) {
        this.board = board;


        //load fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "result-screen-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
