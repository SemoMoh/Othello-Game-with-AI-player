package com.cse.ai.othellogame.gui.gamescreen;

import com.cse.ai.othellogame.HelloApplication;
import com.cse.ai.othellogame.HelloController;
import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.backend.player.AIPlayer;
import com.cse.ai.othellogame.backend.player.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameScreen extends Pane implements Initializable {
    private final ScoreBoard leftBoard;
    private final ScoreBoard rightBoard;
    private final BoardGUI boardGUI;
    private final Board board;
    private final Player playerWhite;
    private final Player playerBlack;

    private final boolean bPlayerAI;
    private final boolean wPlayerAI;

    public static GameScreen gameScreen;

    @FXML
    public Pane root;






    private boolean blackTurn = true;

    public GameScreen(Board board, String playerBlackName, String playerWhiteName,
                      Player playerBlack, Player playerWhite) {
        gameScreen = this;

        this.board = board;
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;

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

        this.bPlayerAI = playerBlack instanceof AIPlayer;
        this.wPlayerAI = playerWhite instanceof AIPlayer;

        // make the first move
        update(false);
        makeMove();
    }

    private void makeMove() {
        if (blackTurn) {
            if (bPlayerAI) {
                this.board.updateBoard(DISK.BLACK, playerBlack.makeMove());
                update(true);
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    makeMove();
                });
            } else {
                updateWithHints(false);
            }
        } else {
            if (wPlayerAI) {
                this.board.updateBoard(DISK.WHITE, playerWhite.makeMove());
                update(true);
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    makeMove();
                });
            } else {
                updateWithHints(false);
            }
        }

    }

    public void makeHumanMove() {
        update(true);
        makeMove();
    }

    public void startGame() {
        while (!board.gameEnded()) {
            if (blackTurn) {
                board.updateBoard(DISK.BLACK, playerBlack.makeMove());
            } else {
                board.updateBoard(DISK.WHITE, playerWhite.makeMove());
            }

            blackTurn = !blackTurn;
            this.update(true);
        }
    }

    public void update(boolean updateTurn) {
        leftBoard.setScore(board.getWhiteScore());
        rightBoard.setScore(board.getBlackScore());
        boardGUI.updateBoard(updateTurn);
        if (updateTurn) {
            if(board.gameEnded()){
                HelloApplication.scene.setRoot(new Label("Game ended"));
                System.out.println("Game ended");
            }
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
            rightBoard.setTurn();
            leftBoard.unsetTurn();
        } else {
            rightBoard.unsetTurn();
            leftBoard.setTurn();
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


    public void restartGame(){
        System.out.println("K30");
        HelloController.restart();

    }

}
