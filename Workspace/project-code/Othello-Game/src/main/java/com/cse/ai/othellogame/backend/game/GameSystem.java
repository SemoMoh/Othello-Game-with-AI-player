package com.cse.ai.othellogame.backend.game;

import com.cse.ai.othellogame.backend.player.HumanPlayer;
import com.cse.ai.othellogame.backend.player.Player;
import com.cse.ai.othellogame.gui.gamescreen.GameScreen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

/**
 * Manages the core logic of the Othello game, including player turns,
 * making moves, and handling game-ending conditions.
 * // TODO: add image of the game flow
 * <p>
 * <img src="path/to/your/image.png" alt="Flow of the game" width="500" height="300">
 * </p>
 */
public class GameSystem {
    /**
     * Timeline for controlling the animation of moves
     */
    private final Timeline timeline;
    private final Board board;
    private final Player playerWhite;
    private final Player playerBlack;
    /**
     * Graphical user interface for the game
     */
    private final GameScreen gameScreen;
    /**
     * Flag indicating whether it's black player's turn or a white player's turn.
     * If true, it's black player's turn, otherwise it's white player's turn.
     */
    private boolean blackTurn;
    /**
     * Index of the last played move.
     */
    private int lastPlayedIndex;
    /**
     * Flag indicating whether a human player has made a move.
     */
    private boolean humanPlayed;
    /**
     * Flag indicating whether hints have been shown for the current player.
     */
    private boolean hintsShown;

    /**
     * Constructs a new GameSystem object with the specified board, players, and game screen.
     *
     * @param board       The game board.
     * @param playerWhite The player controlling the white discs.
     * @param playerBlack The player controlling the black discs.
     * @param gameScreen  The graphical user interface for the game.
     */
    public GameSystem(Board board, Player playerWhite, Player playerBlack, GameScreen gameScreen) {
        this.board = board;
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.gameScreen = gameScreen;

        this.blackTurn = true;
        this.lastPlayedIndex = -1;
        this.humanPlayed = false;
        this.hintsShown = false;

        // Initialize the timeline for the game flow.
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.4), event -> makeMove()));
        // Set the cycle count to INDEFINITE so that the timeline keeps running until the game ends.
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Stops the timeline animation.
     */
    public void stopTimeLine() {
        timeline.stop();
    }

    /**
     * Starts the timeline animation.
     */
    public void startTimeLine() {
        timeline.play();
    }

    /**
     * Executes the next move in the game.
     * <p>
     * This method is responsible for:
     * <ul>
     *   <li>Checking whether the game has ended.</li>
     *   <li>If no hints (valid moves) found, it skips to the next turn.</li>
     *   <li>Handling moves for both human and AI players.</li>
     *   <li>Updating the game board and the game screen accordingly after the move is made.</li>
     * </ul>
     *
     */
    public void makeMove() {
        // Check if the game has ended
        if (board.gameEnded()) {
            // Display the game over a message and stop the timeline
            showGameEnded();
            this.timeline.stop();
            return;
        }

        // Remove no hints message if it is shown
        gameScreen.removeNoHints();
        // Check if no hints are available for the current player
        if (board.noHints()) {
            skipToNextPlayer();
            return;
        }

        // Determine the player whose turn it is
        Player turnPlayer = blackTurn ? playerBlack : playerWhite;

        // Handle move for human player or AI player
        if (turnPlayer instanceof HumanPlayer) {
            if (handleHumanMove()) {
                return;
            }

        } else {
            handleAIMove(turnPlayer);
        }

        // Update the game board with the latest move
        board.updateBoard(blackTurn ? DISK.BLACK : DISK.WHITE, lastPlayedIndex);
        // Switch the turn to the next player
        this.blackTurn = !this.blackTurn;
        // Update the game screen to reflect the changes
        this.gameScreen.update(true, false);
    }

    /**
     * Handles the move of an AI player.
     * <p>
     * This method stops the timeline, allows the AI player to make a move,
     * and then restarts the timeline.
     * <p>
     * It stops the timeline so no overlapping between this and the second move happens,
     * as the AI player can talk some time to make a move.
     * </p>
     *
     * @param turnPlayer The AI player making the move.
     */
    private void handleAIMove(Player turnPlayer) {
        stopTimeLine();
        lastPlayedIndex = turnPlayer.makeMove();
        startTimeLine();
    }

    /**
     * Handles the move of a human player.
     * <p>
     * This method handles the move of a human player. It updates the game screen with hints
     * if they haven't been shown yet and adjusts the timeline speed if necessary.
     * <br>
     * Note: This method waits for the user to click on a hint, and the GUI must call the {@link #setHumanInput(int)}
     * method to inform the system of the selected move.
     * <br>
     * Note:Get the human move index from the {@link #lastPlayedIndex} field after the user.
     * </p>
     *
     * @return {@code false} if the human player move was handled successfully and received an input,
     * {@code true} otherwise to stop the flow of the {@link #makeMove()} method
     * to wait for an input.
     */
    private boolean handleHumanMove() {
        // Show hints if they haven't been shown yet
        if (!hintsShown) {
            this.gameScreen.update(false, true);
            hintsShown = true;
        }

        // Adjust timeline speed if human move hasn't been made yet
        if (!humanPlayed) {
            changeTimeLineSpeed(60);
            return true;
        }
        humanPlayed = false;
        hintsShown = false;
        changeTimeLineSpeed(1);
        return false;
    }

    /**
     * Notifies the game screen that the game has ended.
     */
    private void showGameEnded() {
        System.out.println("Game ended, Winner: " + board.getWinner());
        this.gameScreen.gameEnded();
    }

    /**
     * Skips to the next player when there are no hints available for the current player.
     */
    private void skipToNextPlayer() {
        // The game is not ended but there are no hints for the current player
        System.out.println("No hints for the current player");
        // Show no hints message on the game screen
        this.gameScreen.showNoHints();
        // Update turn
        this.blackTurn = !this.blackTurn;

        // As now moves happened, the board is not updated, so we have to update the hints
        // for the next turn manually
        board.generateNewHints(this.blackTurn ? DISK.BLACK : DISK.WHITE);
    }

    /**
     * Sets the index of the last played move by a human player.
     * <br>
     * This method is called by the GUI to inform the system of the selected move.
     *
     * @param index The index of the last played move.
     */
    public void setHumanInput(int index) {
        if (!humanPlayed) {
            lastPlayedIndex = index;
            humanPlayed = true;
        }
    }

    /**
     * Checks if it is currently the black player's turn.
     *
     * @return {@code true} if it is currently the black player's turn, {@code false} for the white player's turn.
     */
    public boolean isBlackPlayerTurn() {
        return blackTurn;
    }

    /**
     * Changes the speed of the timeline flow.
     *
     * @param rateMultiplier The multiplier for the timeline speed, higher values mean faster speed.
     */
    private void changeTimeLineSpeed(double rateMultiplier) {
        timeline.setRate(rateMultiplier);
    }

    /**
     * Gets the index of the last played move.
     *
     * @return The index of the last played move.
     */
    public int getLastPlayedIndex() {
        return lastPlayedIndex;
    }
}
