package com.cse.ai.othellogame.gui.gamescreen;

import com.cse.ai.othellogame.MainGUI;
import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.GameSystem;
import com.cse.ai.othellogame.backend.player.Player;
import com.cse.ai.othellogame.gui.mainmenu.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the main class for the game screen; it will hold the game board, the score board and the game system.
 */
public class GameScreen extends Pane implements Initializable {
    private final ScoreBoard leftBoard;
    private final ScoreBoard rightBoard;
    private final BoardGUI boardGUI;
    private final Board board;
    public GameSystem gameSystem;
    @FXML
    public Pane root;
    @FXML
    public Button restartButton;
    @FXML
    public Button closeButton;
    @FXML
    public Button mainMenuButton;
    private final String playerBlackName;
    private final String playerWhiteName;
    private boolean noHintsState;


    /**
     * Constructs a GameScreen object.
     *
     * @param board           The game board.
     * @param playerBlackName The name of the black player.
     * @param playerWhiteName The name of the white player.
     * @param playerBlack     The black player.
     * @param playerWhite     The white player.
     */
    public GameScreen(Board board, String playerBlackName, String playerWhiteName,
                      Player playerBlack, Player playerWhite) {
        this.board = board;
        this.playerBlackName = playerBlackName;
        this.playerWhiteName = playerWhiteName;
        // Create the score boards
        leftBoard = new ScoreBoard(playerWhiteName, false);
        rightBoard = new ScoreBoard(playerBlackName, true);
        // Create the game system
        this.gameSystem = new GameSystem(board, playerWhite, playerBlack, this);
        // Create the game board GUI
        boardGUI = new BoardGUI(board, gameSystem);

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


        // make the first move
        update(false, false);

        // start the game flow
        gameSystem.startTimeLine();
    }

    /**
     * Updates the game screen based on the current state of the game board.
     *
     * @param updateTurn Whether to update the turn or not.
     * @param withHints  Whether to show hints for human player or not.
     */
    public void update(boolean updateTurn, boolean withHints) {
        leftBoard.setScore(board.getWhiteScore());
        rightBoard.setScore(board.getBlackScore());
        boardGUI.updateBoard(withHints);

        if (updateTurn) {
            updateTurn();
        }
    }


    /**
     * Updates the turn of the current player on the score boards.
     */
    private void updateTurn() {
        if (this.gameSystem.isBlackPlayerTurn()) {
            rightBoard.setTurn();
            leftBoard.unsetTurn();
        } else {
            rightBoard.unsetTurn();
            leftBoard.setTurn();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // add the score boards and the game board to the root
        this.leftBoard.setLayoutX(206);
        this.leftBoard.setLayoutY(45);

        this.rightBoard.setLayoutX(1060);
        this.rightBoard.setLayoutY(45);

        this.boardGUI.setLayoutX(640);
        this.boardGUI.setLayoutY(344);

        root.getChildren().addAll(leftBoard, rightBoard, boardGUI);

        // hover effect for the buttons

        // restart button
        restartButton.setOnMouseEntered(e -> restartButton.setStyle(
                "-fx-background-color:  rgba(0, 0, 0);" +
                        "-fx-background-radius: 4px"));
        restartButton.setOnMouseExited(e -> restartButton.setStyle(
                "-fx-background-color:  rgba(0, 0, 0, 0.8);" +
                        "-fx-background-radius: 4px"));

        // main menu button
        mainMenuButton.setOnMouseEntered(e -> mainMenuButton.setStyle(
                "-fx-background-color:  rgba(0, 0, 0);" +
                        "-fx-background-radius: 4px"));
        mainMenuButton.setOnMouseExited(e -> mainMenuButton.setStyle(
                "-fx-background-color:  rgba(0, 0, 0, 0.8);" +
                        "-fx-background-radius: 4px"));

        // close button
        closeButton.setOnMouseEntered(e -> closeButton.setStyle(
                "-fx-background-color:   rgba(180, 0, 0);" +
                        "-fx-background-radius: 4px"));
        closeButton.setOnMouseExited(e -> closeButton.setStyle(
                "-fx-background-color:   rgba(180, 0, 0, 0.8);" +
                        "-fx-background-radius: 4px"));

    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    /**
     * Restarts the game.
     * It shows a dialog box to confirm the restart.
     */
    public void restartGame() {
        // show dialog and wait for a response
        dialogBox("Restart Game");
    }

    /**
     * Closes the game application.
     * It shows a dialog box to confirm the close.
     */
    public void closeGame() {
        // Show the dialog and wait for a response
        dialogBox("Close Game");
    }

    /**
     * Goes back to the main menu.
     * It shows a dialog box to confirm the action.
     */
    public void mainMenu() {
        // Show the dialog and wait for a response
        dialogBox("Main Menu");
    }

    /**
     * Shows a dialog box with the given title.
     *
     * @param title The title of the dialog box.
     */
    private void dialogBox(String title) {
        // stop the gameSystem flow until we get the response
        this.gameSystem.stopTimeLine();

        // show dialog
        DialogBox dialogBox = new DialogBox(title, this);
        dialogBox.setLayoutX(465);
        dialogBox.setLayoutY(234);
        root.getChildren().add(dialogBox);

        // wait for the response
    }

    /**
     * Sets the response of the dialog box and the action based on that response.
     *
     * @param response The response of the dialog box.
     * @param title    The title of the dialog box, to determine what action to do.
     */
    public void setDialogBoxResponse(boolean response, String title) {
        // remove the dialog box
        root.getChildren().remove(root.getChildren().size() - 1);

        if (!response) {
            // Cancel button pressed so we need to resume the game
            this.gameSystem.startTimeLine();
            return;
        }

        // Confirm button pressed
        this.gameSystem.stopTimeLine();
        this.gameSystem = null;

        // know witch button was pressed to take the right action
        switch (title) {
            case "Restart Game":
                MainMenu.restartTheGame();
                break;
            case "Close Game":
                MainGUI.endGame();
                break;
            case "Main Menu":
                MainGUI.mainMenu();
                break;
        }
    }


    // Show the user that the current player doesn't have any possible moves, so the
    // turn will change to the next player

    /**
     * Shows a message to the user that the current player doesn't have any possible moves.
     * <br>
     * The {@link #gameSystem} will remove the message and change the turn to the next player after 0.6 second.
     */
    public void showNoHints() {
        // Create a stack pane to hold the frame and text
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        // Create a rectangle for the frame
        Rectangle frame = new Rectangle(540, 302);
        frame.setFill(Color.rgb(13, 13, 13, 0.74));
        frame.setArcWidth(42);
        frame.setArcHeight(42);
        frame.setEffect(new DropShadow(5, Color.BLACK)); // Add shadow effect

        // Create text for the message
        Text message = new Text("No possible move for this turn");
        message.setFont(Font.font("Inter", FontWeight.findByWeight(500), 42));
        message.setFill(Color.WHITE);
        message.setWrappingWidth(516);
        message.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        message.setEffect(new DropShadow(5, Color.BLACK)); // Add shadow effect

        // Add the rectangle and text to the stack pane
        stackPane.getChildren().addAll(frame, message);

        stackPane.setLayoutX(688);
        stackPane.setLayoutY(442);
        // Add the stack pane to the root pane
        root.getChildren().add(stackPane);

        this.noHintsState = true;
    }

    /**
     * Removes the message that the current player doesn't have any possible moves as its turn was switched.
     */
    public void removeNoHints() {
        if (noHintsState) {
            root.getChildren().remove(root.getChildren().size() - 1);
            this.noHintsState = false;
        }
    }

    /**
     * Show the result screen after the game is ended.
     */
    public void gameEnded() {
        // TODO:
        // ResultScreen resultScreen = new ResultScreen(board, playerBlackName, playerWhiteName, boardGUI);
        // MainGUI.scene.setRoot(resultScreen);
    }
}
