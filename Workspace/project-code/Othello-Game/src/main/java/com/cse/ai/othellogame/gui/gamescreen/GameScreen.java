package com.cse.ai.othellogame.gui.gamescreen;

import com.cse.ai.othellogame.backend.game.Board;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameScreen extends Pane implements Initializable {
    private final ScoreBoard leftBoard;
    private final ScoreBoard rightBoard;
    private final BoardGUI boardGUI;
    private final Board board;

    @FXML
    public Pane root;

    private boolean blackTurn = true;

    public GameScreen(Board board, String playerBlackName, String playerWhiteName) {
        this.board = board;

        leftBoard = new ScoreBoard(playerWhiteName, false);
        rightBoard = new ScoreBoard(playerBlackName, true);
        boardGUI = new BoardGUI(board);

        //load fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "game-screen-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void update(boolean updateTurn) {
        leftBoard.setScore(board.getWhiteScore());
        rightBoard.setScore(board.getBlackScore());
        boardGUI.updateBoard(updateTurn);
        if (updateTurn) {
            updateTurn();
        }
    }

    public void updateWithHints(boolean updateTurn) {
        leftBoard.setScore(board.getWhiteScore());
        rightBoard.setScore(board.getBlackScore());
        boardGUI.updateBoardWithHints(updateTurn);
        if (updateTurn) {
            updateTurn();
        }
    }

    private void updateTurn() {
        blackTurn = !blackTurn;

        if (blackTurn) {
            rightBoard.unsetTurn();
            leftBoard.setTurn();
        } else {
            rightBoard.setTurn();
            leftBoard.unsetTurn();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.leftBoard.setLayoutX(206);
        this.leftBoard.setLayoutY(45);

        this.rightBoard.setLayoutX(1060);
        this.rightBoard.setLayoutY(45);

        this.boardGUI.setLayoutX(640);
        this.boardGUI.setLayoutY(344);

        updateTurn();

        root.getChildren().addAll(leftBoard, rightBoard, boardGUI);
    }


    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    public int getInput() {
        updateWithHints(false);
        return boardGUI.getInput();
    }
}
