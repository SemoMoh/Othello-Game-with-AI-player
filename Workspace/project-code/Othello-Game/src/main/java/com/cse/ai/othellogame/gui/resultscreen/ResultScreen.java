package com.cse.ai.othellogame.gui.resultscreen;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.gui.gamescreen.BoardGUI;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultScreen extends Pane implements Initializable {
    private final Board board;
    private final BoardGUI boardGUI;
    public ResultScreen(Board board, String playerBlackName, String playerWhiteName, BoardGUI boardGUI) {
        this.board = board;
        this.boardGUI = boardGUI;

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
