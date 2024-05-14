package com.cse.ai.othellogame.backend.game;

import com.cse.ai.othellogame.backend.player.HumanPlayer;
import com.cse.ai.othellogame.backend.player.Player;
import com.cse.ai.othellogame.gui.gamescreen.GameScreen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class GameSystem {
    private final Timeline timeline;
    private final Board board;
    private final Player playerWhite;
    private final Player playerBlack;
    private final GameScreen gameScreen;
    private boolean blackTurn;
    private boolean gameEnded;
    // used to keep track of the last-played index
    private int lastPlayedIndex;
    private boolean humanPlayed;
    private boolean hintsShown;

    public GameSystem(Board board, Player playerWhite, Player playerBlack, GameScreen gameScreen) {
        this.board = board;
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.gameScreen = gameScreen;

        this.blackTurn = true;
        this.gameEnded = false;
        this.lastPlayedIndex = -1;
        this.blackTurn = true;
        this.hintsShown = false;


        timeline = new Timeline(new KeyFrame(Duration.seconds(0.6), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeMove();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

    }

    public void stopTimeLine() {
        timeline.stop();
    }

    public void startTimeLine() {
        timeline.play();
    }

    public void makeMove() {
        // get the game ended value
        gameEnded = board.gameEnded();
        DISK colorTurn = blackTurn ? DISK.BLACK : DISK.WHITE;
        Player turnPlayer = blackTurn ? playerBlack : playerWhite;
        if (gameEnded) {
            // TODO
            showGameEnded();
            this.timeline.stop();
            return;
        }

        gameScreen.removeNoHints();

        if (board.noHints()) {
            skipToNextPlayer(colorTurn, turnPlayer);
            return;
        }

        if (turnPlayer instanceof HumanPlayer) {
            // TODO
            if (!hintsShown) {
                this.gameScreen.updateWithHints(false);
                hintsShown = true;
            }

            if (!humanPlayed) {
                changeTimeLineSpeed(60);
                return;
            }
            humanPlayed = false;
            hintsShown = false;
            /*            changeTimeLineSpeed(1);*/
        } else {
            // AI player
            stopTimeLine();
            lastPlayedIndex = turnPlayer.makeMove();
            startTimeLine();
        }

        board.updateBoard(colorTurn, lastPlayedIndex);
        this.blackTurn = !this.blackTurn;
        this.gameScreen.update(true);
        changeTimeLineSpeed(1);
    }

    private void showGameEnded() {
        System.out.println("Game ended, Winner: " + board.getWinner());
    }

    private void skipToNextPlayer(DISK colorTurn, Player turnPlayer) {
        // The game is not ended but there are no hints for the current player
        System.out.println("No hints for the current player");
        this.gameScreen.showNoHints();
        // Update turn
        this.blackTurn = !this.blackTurn;

        // As now moves happened, the board is not updated, so we have to update the hints
        // for the next turn manually
        DISK nextColorTurn = this.blackTurn ? DISK.BLACK : DISK.WHITE;
        board.generateNewHints(nextColorTurn);
    }

    public void setHumanInput(int index) {
        if (!humanPlayed) {
            lastPlayedIndex = index;
            humanPlayed = true;
        }
    }

    public boolean isBlackTurn() {
        return blackTurn;
    }

    private void changeTimeLineSpeed(double rateMultiplier) {
        timeline.setRate(rateMultiplier);
    }

    public int getLastPlayedIndex() {
        return lastPlayedIndex;
    }
}
