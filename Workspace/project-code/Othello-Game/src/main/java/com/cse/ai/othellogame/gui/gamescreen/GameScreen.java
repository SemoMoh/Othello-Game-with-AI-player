package com.cse.ai.othellogame.gui.gamescreen;

import com.cse.ai.othellogame.HelloApplication;
import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.backend.player.AIPlayer;
import com.cse.ai.othellogame.backend.player.Player;
import com.cse.ai.othellogame.gui.mainmenu.MainMenu;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

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

    private boolean flowEnded = false;
    private boolean gameEnded = false;
    public static GameScreen gameScreen;

    @FXML
    public Pane root;

    private Boolean blackTurn = true;

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
                Platform.runLater(() -> {
                    update(false);
                    delay(0.5F);
                    if (!gameEnded && !flowEnded) {
                        // The game is not ended but there are no hints for the current player
                        if (board.noHints()) {
                            // show there are no hints for the current player, and the turn will change to the next player
                            System.out.println("No hints for the current player");
                            delay(0.5F);
                            board.generateNewHints(DISK.WHITE);
                            update(true);
                        } else {
                            this.board.updateBoard(DISK.BLACK, playerBlack.makeMove());
                            update(true);
                            delay(0.1F);
                        }
                        makeMove();
                    } else {
                        System.out.println("Game ended, Winner: " + board.getWinner());
                    }
                });
            } else {
                if (!gameEnded && !flowEnded) {
                    // The game is not ended but there are no hints for the current player
                    if (board.noHints()) {
                        // show there are no hints for the current player, and the turn will change to the next player
                        System.out.println("No hints for the current player");
                        delay(0.5F);
                        board.generateNewHints(DISK.WHITE);
                        update(true);
                        delay(0.1F);
                        makeMove();
                    } else {
                        updateWithHints(false);
                    }
                }
            }
        } else {
            if (wPlayerAI) {
                Platform.runLater(() -> {
                    delay(0.5F);

                    if (!gameEnded && !flowEnded) {
                        // The game is not ended but there are no hints for the current player
                        if (board.noHints()) {
                            // show there are no hints for the current player, and the turn will change to the next player
                            System.out.println("No hints for the current player");
                            delay(0.5F);
                            board.generateNewHints(DISK.BLACK);
                            update(true);
                        } else {
                            this.board.updateBoard(DISK.WHITE, playerWhite.makeMove());
                            update(true);
                        }
                        makeMove();
                    } else {
                        System.out.println("Game ended, Winner: " + board.getWinner());
                    }
                });
            } else {
                if (!gameEnded && !flowEnded) {
                    // The game is not ended but there are no hints for the current player
                    if (board.noHints()) {
                        // show there are no hints for the current player, and the turn will change to the next player
                        System.out.println("No hints for the current player");
                        delay(0.5F);
                        board.generateNewHints(DISK.BLACK);
                        update(true);
                        makeMove();
                    } else {
                        updateWithHints(false);
                    }
                }
            }
        }

    }

    private static void delay(float timeSec) {
        for (int i = 0; i < 10 * timeSec; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void makeHumanMove() {
        update(true);
        delay(0.1F);
        makeMove();
    }


    public void update(boolean updateTurn) {
        leftBoard.setScore(board.getWhiteScore());
        rightBoard.setScore(board.getBlackScore());
        boardGUI.updateBoard(updateTurn);
        if (updateTurn) {
            gameEnded = board.gameEnded();
            if (gameEnded) {
                char winner = board.getWinner();
                //HelloApplication.scene.setRoot(new Label("Game ended, Winner: " + winner));
                flowEnded = true;
                System.out.println("Game ended, Winner: " + winner);
            }
            updateTurn();
        }
        delay(0.1F);
    }

    public void updateWithHints(boolean updateTurn) {
        leftBoard.setScore(board.getWhiteScore());
        rightBoard.setScore(board.getBlackScore());
        boardGUI.updateBoardWithHints(updateTurn);
        if (updateTurn) {
            if (board.gameEnded()) {
                char winner = board.getWinner();
                //HelloApplication.scene.setRoot(new Label("Game ended, Winner: " + winner));
                flowEnded = true;
                System.out.println("Game ended, Winner: " + winner);
            }
            updateTurn();
        }
    }

    private void updateTurn() {

        blackTurn = !blackTurn;

        boardGUI.updateTurn();
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


    public void restartGame() {
        MainMenu.restartTheGame();
        flowEnded = true;
    }

    public void closeGame() {
        flowEnded = true;
        HelloApplication.endGame();
    }

}
