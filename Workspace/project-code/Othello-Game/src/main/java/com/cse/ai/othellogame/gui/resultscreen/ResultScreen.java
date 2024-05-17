package com.cse.ai.othellogame.gui.resultscreen;

import com.cse.ai.othellogame.MainGUI;
import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.gui.gamescreen.BoardGUI;
import com.cse.ai.othellogame.gui.mainmenu.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The ResultScreen class represents the screen displayed after the game ends.
 * It shows the final scores, winner or tie message, and provides options to
 * restart the game, return to the main menu, or exit the application.
 */
public class ResultScreen extends Pane implements Initializable {

    // GUI components defined in the FXML file
    @FXML
    public Pane root;
    @FXML
    public Label whitePlayerNameLabel;
    @FXML
    public Label blackPlayerNameLabel;
    @FXML
    public Text blackScore;
    @FXML
    public Text whiteScore;
    @FXML
    public Label whoIsTheWinner;
    @FXML
    public Label winnerOrTieLabel;
    @FXML
    public HBox exitButton;
    @FXML
    public HBox restartButton;
    @FXML
    public HBox mainMenuButton;

    // Variables
    private final Board board;
    private final BoardGUI boardGUI;
    private final String playerBlackName;
    private final String playerWhiteName;

    // Load the sound effect for the click
    private final MediaPlayer clickSound = new MediaPlayer(
            new Media(
                    Objects.requireNonNull(ResultScreen.class.getResource("/sound effects/click-buttons-sounds.mp3")).toString()
            )
    );

    /**
     * Constructs the ResultScreen with the provided game board, player names,
     * and the corresponding BoardGUI.
     *
     * @param board           The game board.
     * @param playerBlackName The name of the black player.
     * @param playerWhiteName The name of the white player.
     * @param boardGUI        The GUI representation of the game board.
     */
    public ResultScreen(Board board, String playerBlackName, String playerWhiteName, BoardGUI boardGUI) {
        this.board = board;
        this.playerBlackName = playerBlackName;
        this.playerWhiteName = playerWhiteName;
        this.boardGUI = boardGUI;

        // Load FXML file and initialize components
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("result-screen-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Displays the winner or tie message based on the game result.
     */
    private void displayWinner() {
        char winner = board.getWinner();
        switch (winner) {
            case 'B':
                winnerOrTieLabel.setText("Winner");
                whoIsTheWinner.setText("Black Player");
                break;
            case 'W':
                winnerOrTieLabel.setText("Winner");
                whoIsTheWinner.setText("White Player");
                break;
            case 'T':
                winnerOrTieLabel.setText("Tie");
                whoIsTheWinner.setText("Good Game \uD83E\uDD1D");
                break;
        }
    }

    /**
     * Initializes the ResultScreen with player names, scores, and displays the winner.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set player names and scores
        blackPlayerNameLabel.setText(playerBlackName);
        whitePlayerNameLabel.setText(playerWhiteName);
        blackScore.setText(Integer.toString(board.getBlackScore()));
        whiteScore.setText(Integer.toString(board.getWhiteScore()));

        // Add BoardGUI to the root pane
        root.getChildren().add(1, boardGUI);

        // Display winner or tie message
        displayWinner();


        exitButton.setOnMouseEntered(e -> exitButton.setStyle(
                "-fx-background-color:  #673232FF;" +
                        "-fx-background-radius: 16px;"
                        + "-fx-border-color: white;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 16px;"
        ));

        exitButton.setOnMouseExited(e -> exitButton.setStyle(
                "-fx-background-color:    #8E3030;" +
                        "-fx-background-radius: 16px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 16px;"
        ));

        // for `cancel button`
        mainMenuButton.setOnMouseEntered(e -> mainMenuButton.setStyle(
                "-fx-background-color:  #294930  ;" +
                        "-fx-background-radius: 16px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 16px;"
        ));

        mainMenuButton.setOnMouseExited(e -> mainMenuButton.setStyle(
                "-fx-background-color:   #3C6539;" +
                        "-fx-background-radius: 16px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 16px;"
        ));


        restartButton.setOnMouseEntered(e -> restartButton.setStyle(
                "-fx-background-color:   #294930;" +
                        "-fx-background-radius: 16px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 16px;"
        ));

        restartButton.setOnMouseExited(e -> restartButton.setStyle(
                "-fx-background-color:  #3C6539;" +
                        "-fx-background-radius: 16px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 16px;"
        ));


    }

    /**
     * Closes the game application.
     */
    public void closeApp() {
        // add click sound
        clickSound.setVolume(0.6);
        clickSound.stop();
        clickSound.play();

        MainGUI.endGame();
    }

    /**
     * Returns to the main menu.
     */
    public void mainMenu() {
        // add click sound
        clickSound.setVolume(0.6);
        clickSound.stop();
        clickSound.play();

        MainGUI.mainMenu();
    }

    /**
     * Restarts the game.
     */
    public void restartGame() {
        // add click sound
        clickSound.setVolume(0.6);
        clickSound.stop();
        clickSound.play();

        MainMenu.restartTheGame();
    }

    /**
     * Returns the styleable node of the ResultScreen.
     *
     * @return The styleable node.
     */
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
