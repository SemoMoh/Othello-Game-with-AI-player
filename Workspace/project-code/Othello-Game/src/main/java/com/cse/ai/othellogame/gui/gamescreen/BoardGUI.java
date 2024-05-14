package com.cse.ai.othellogame.gui.gamescreen;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.backend.game.GameSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Represents the graphical user interface of the game board.
 */
public class BoardGUI extends AnchorPane implements Initializable {
    private final Board board;
    private final GameSystem gameSystem;

    @FXML
    public GridPane boardPane;
    @FXML
    public AnchorPane root;

    private int clickedIndex = -1;

    /**
     * Constructs a new BoardGUI object.
     *
     * @param board      The game board to visualize.
     * @param gameSystem The game system to call when a human make a move.
     */
    public BoardGUI(Board board, GameSystem gameSystem) {
        this.board = board;
        this.gameSystem = gameSystem;

        //load fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "board-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // show the initial state of the board
        updateBoard(false);
    }

    /**
     * <p>Updates the graphical user interface of the game board to reflect the current state.</p>
     * <ul>
     *     <li>Sets the images of the cells based on the state of the board.</li>
     *     <li>Plays a sound effect if a disk is flipped or placed.</li>
     *     <li>Marks the last played cell.</li>
     * </ul>
     *
     * @param withHints A boolean value indicating whether to display hints for human player or not.
     */
    public void updateBoard(boolean withHints) {
        for (Node node : boardPane.getChildren()) {
            Cell cell = (Cell) node;
            int index = cell.getIndex();

            // Play sound if disk is flipped or placed
            if (board.getPos(index) != cell.getState()) {
                cell.playSound();
            }

            // Set the displayed image of the cell to the actual disk state
            DISK state = board.getPos(index);
            if (state != DISK.HINT && state != DISK.BLACK_HINT && state != DISK.WHITE_HINT) {
                cell.setDisplayedImage(state);
            } else if(withHints) {
                // Set the displayed image of the cell to the corresponding hint color
                if (gameSystem.isBlackPlayerTurn()) {
                    cell.setDisplayedImage(DISK.BLACK_HINT);
                } else {
                    cell.setDisplayedImage(DISK.WHITE_HINT);
                }
            } else {
                // Hide the hints by setting the cell's image to empty
                cell.setDisplayedImage(DISK.EMPTY);
            }

            if (index == gameSystem.getLastPlayedIndex() && gameSystem.getLastPlayedIndex() != -1) {
                // Mark the last played cell
                cell.markLastPlayed();
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialize the board GUI cells
        int index = 0;
        for (Node cell : boardPane.getChildren()) {
            // set the index of the cell
            ((Cell) cell).setIndex(index++);
            // set the event handler for the cell when clicked
            cell.setOnMouseClicked(event -> {
                // Return the node that was clicked on
                Node clickedNode = (Node) event.getSource();
                if (clickedNode instanceof Cell clickedCell) {
                    boolean validInput = clickedCell.changeState(event);

                    if (validInput) {
                        clickedIndex = clickedCell.getIndex();
                        // set the human input to the game system to continue the game.
                        gameSystem.setHumanInput(clickedIndex);
                        System.out.println("Clicked on: " + clickedNode);
                    }
                }
            });
        }
        // show the initial state of the board
        // updateBoard(false);
    }

    /**
     * Gets the index of the last cell where the human player clicked.
     *
     * @return The index of the clicked cell.
     */
    public int getInput() {
        int result = clickedIndex;
        clickedIndex = -1;
        return result;
    }

    @Override
    public Node getStyleableNode() {
        return this;
    }

}