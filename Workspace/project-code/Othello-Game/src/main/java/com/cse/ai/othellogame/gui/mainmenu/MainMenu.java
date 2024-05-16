
package com.cse.ai.othellogame.gui.mainmenu;

import com.cse.ai.othellogame.MainGUI;
import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.backend.player.AIPlayer;
import com.cse.ai.othellogame.backend.player.HumanPlayer;
import com.cse.ai.othellogame.backend.player.Player;
import com.cse.ai.othellogame.gui.gamescreen.GameScreen;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The MainMenu class represents the main menu screen of the Othello game.
 * It allows players to configure game settings such as player types (Human or AI),
 * player names, and AI difficulty levels before starting a new game.
 * <p>
 * This class extends the JavaFX Pane class and implements the Initializable interface
 * to initialize the GUI components defined in the corresponding FXML file.
 * <p>
 * The MainMenu class contains methods to handle user interactions, such as toggling
 * player types between Humans and AI, changing AI difficulty levels, retrieving player
 * names, and starting a new game with the selected settings.
 */
public class MainMenu extends Pane implements Initializable {


    public static MainMenu mainMenu;

    @FXML
    public Pane root;

    @FXML
    public Button whitePlayerType;
    @FXML
    public TextField whitePlayerName;
    @FXML
    public Button whiteDifficulty;


    @FXML
    public Button blackPlayerType;

    @FXML
    public TextField blackPlayerName;


    @FXML
    public Button blackDifficulty;
    @FXML
    public Button startNewGameButton;


    /**
     * Constructs the main menu screen by loading the corresponding FXML file.
     * Initializes GUI components and event handlers defined in the FXML file.
     */
    public MainMenu() {

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

    /**
     * Restarts the game with the same settings
     */
    public static void restartTheGame() {
        mainMenu.startTheGame();
    }

    /**
     * Toggles the player type between AI and Human for the white player.
     */
    public void changeWhitePlayerType() {
        if (whitePlayerType.getText().equals("AI"))
            whitePlayerType.setText("Human");
        else
            whitePlayerType.setText("AI");
    }

    /**
     * Toggles the player type between AI and Human for the black player.
     */
    public void changeBlackPlayerType() {
        if (blackPlayerType.getText().equals("AI"))
            blackPlayerType.setText("Human");
        else
            blackPlayerType.setText("AI");
    }

    /**
     * Changes the AI difficulty level for the white player.
     */
    public void changeWhiteDifficulty() {
        if (whiteDifficulty.getText().equals("Difficulty: Easy"))
            whiteDifficulty.setText("Difficulty: Medium");
        else if (whiteDifficulty.getText().equals("Difficulty: Medium")) {
            whiteDifficulty.setText("Difficulty: Hard");
        } else
            whiteDifficulty.setText("Difficulty: Easy");
    }

    /**
     * Changes the AI difficulty level for the black player.
     */
    public void changeBlackDifficulty() {
        if (blackDifficulty.getText().equals("Difficulty: Easy"))
            blackDifficulty.setText("Difficulty: Medium");
        else if (blackDifficulty.getText().equals("Difficulty: Medium")) {
            blackDifficulty.setText("Difficulty: Hard");
        } else
            blackDifficulty.setText("Difficulty: Easy");
    }

    /**
     * Retrieves the name of the black player.
     */

    public String getBlackPlayerName() {
        if (!blackPlayerName.getText().isEmpty()) {
            return blackPlayerName.getText();
        }
        return "Black Player";
    }

    /**
     * Retrieves the name of the white player.
     */

    public String getWhitePlayerName() {
        if (!whitePlayerName.getText().isEmpty()) {
            return whitePlayerName.getText();
        }
        return "White Player";
    }

    /**
     * Starts a new game with the selected settings.
     */
    public void startTheGame() {
        //System.out.println("K30");

        //HelloController.restart();

        Board board = new Board();

        Player blackPlayer;
        Player whitePlayer;

        String blackName;
        if (isAIPlayer(blackPlayerType)) {
            blackPlayer = new AIPlayer(board, DISK.BLACK, getDifficultyLevel(blackDifficulty.getText()));
            blackName = "Batman";
        } else {
            blackPlayer = new HumanPlayer(board, DISK.BLACK);
            blackName = getBlackPlayerName();
        }

        String whiteName;
        if (isAIPlayer(whitePlayerType)) {
            whitePlayer = new AIPlayer(board, DISK.WHITE, getDifficultyLevel(whiteDifficulty.getText()));
            whiteName = "The joker";
        } else {
            whitePlayer = new HumanPlayer(board, DISK.WHITE);
            whiteName = getWhitePlayerName();
        }

        GameScreen g = new GameScreen(board, blackName, whiteName, blackPlayer, whitePlayer);
        MainGUI.scene.setRoot(g);


    }
    // Helper methods

    /**
     * Determines if the player type is AI based on the button text.
     */

    private boolean isAIPlayer(Button playerTypeButton) {
        return playerTypeButton.getText().equals("AI");
    }

    /**
     * Retrieves the difficulty level based on the button text.
     */
    private int getDifficultyLevel(String buttonText) {
        return switch (buttonText) {
            case "Difficulty: Easy" -> 1;
            case "Difficulty: Medium" -> 2;
            case "Difficulty: Hard" -> 8;
            default -> throw new IllegalArgumentException("Invalid difficulty level: " + buttonText + 1);
        };
    }


    /**
     * Closes the game application.
     *  TODO:It shows a dialog box to confirm the close.
     */
    public void closeGame() {
        // Show the dialog and wait for a response
        MainGUI.endGame();
    }


    // Initialization method

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //root.getChildren().add(this);
        // Bind visibility of text fields and buttons based on a player type
        blackPlayerName.visibleProperty().bind(Bindings.createBooleanBinding(() -> blackPlayerType.getText().equals("Human"), blackPlayerType.textProperty()));
        whitePlayerName.visibleProperty().bind(Bindings.createBooleanBinding(() -> whitePlayerType.getText().equals("Human"), whitePlayerType.textProperty()));
        whiteDifficulty.visibleProperty().bind(Bindings.createBooleanBinding(() -> whitePlayerType.getText().equals("AI"), whitePlayerType.textProperty()));
        blackDifficulty.visibleProperty().bind(Bindings.createBooleanBinding(() -> blackPlayerType.getText().equals("AI"), blackPlayerType.textProperty()));
    }
}
