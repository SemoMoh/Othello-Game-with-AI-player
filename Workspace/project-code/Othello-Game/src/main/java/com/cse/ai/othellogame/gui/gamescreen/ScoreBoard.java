package com.cse.ai.othellogame.gui.gamescreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Represents the score board displayed in the game screen.
 */
public class ScoreBoard extends AnchorPane implements Initializable {
    // CSS styles for the board
    private static final String whiteNormalRecColor = "-fx-background-color: linear-gradient(to right, rgba(2, 2, 2, 0) 0%, rgba(14, 45, 41, 0.72) 43.5%, rgba(6, 38, 21, 0.9) 97.5%);";
    private static final String whiteTurnRecColor = "-fx-background-color: linear-gradient(to right, rgba(0, 0, 0, 0.8) 0%, rgba(14, 45, 41, 0.72) 43.5%, #000000 97.5%)linear-gradient(to top, rgba(0, 0, 0, 0.8) 0%, rgba(14, 45, 41, 0.72) 43.5%, #000000 97.5%);";
    private static final String blackNormalRecColor = "-fx-background-color: linear-gradient(to left, rgba(2, 2, 2, 0) 0%, rgba(14, 45, 41, 0.72) 43.5%, rgba(6, 38, 21, 0.9) 97.5%);";
    private static final String blackTurnRecColor = "-fx-background-color: linear-gradient(to left, rgba(0, 0, 0, 0.8) 0%, rgba(14, 45, 41, 0.72) 43.5%, #000000 97.5%)linear-gradient(to top, rgba(0, 0, 0, 0.8) 0%, rgba(14, 45, 41, 0.72) 43.5%, #000000 97.5%);";
    private static final String recBorderStyle = "-fx-border-color: transparent;";
    /**
     * True if the score board is for the black player, false otherwise.
     * This is used to determine which score board fxml file to load.
     */
    private final boolean forBlack;
    // colors for the score board
    private final String normalColor;
    private final String turnColor;

    // player name to display
    private final String playerNameString;


    @FXML
    Label scoreNumber;
    @FXML
    Label turnLabel;
    @FXML
    StackPane scoreRec;
    @FXML
    Label playerName;


    /**
     * Constructs a new score board.
     *
     * @param playerName The name of the player.
     * @param forBlack   Indicates if the score board is for the black player.
     */
    public ScoreBoard(String playerName, boolean forBlack) {
        this.forBlack = forBlack;
        this.playerNameString = playerName;

        // set colors to use based on player
        if (forBlack) {
            normalColor = blackNormalRecColor;
            turnColor = blackTurnRecColor;
        } else {
            normalColor = whiteNormalRecColor;
            turnColor = whiteTurnRecColor;
        }

        //load fxml file
        FXMLLoader fxmlLoader;
        // load the correct fxml file based on player
        if (forBlack) {
            fxmlLoader = new FXMLLoader(getClass().getResource(
                    "right-score-board-view.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(getClass().getResource(
                    "left-score-board-view.fxml"));
        }
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Sets the score board to display the player's turn.
     */
    public void setTurn() {
        turnLabel.setTextFill(Color.WHITE);
        scoreRec.setStyle(turnColor + recBorderStyle);
    }

    /**
     * Sets the score board to hide the player's turn.
     */
    public void unsetTurn() {
        turnLabel.setTextFill(Color.TRANSPARENT);
        scoreRec.setStyle(normalColor + recBorderStyle);
    }

    /**
     * Sets the score on the score board.
     *
     * @param score The score to set.
     */
    public void setScore(int score) {
        scoreNumber.setText(Integer.toString(score));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setScore(2);
        playerName.setText(playerNameString);

        if (forBlack) {
            // show turn
            setTurn();
        } else {
            // hide turn
            unsetTurn();
        }
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
