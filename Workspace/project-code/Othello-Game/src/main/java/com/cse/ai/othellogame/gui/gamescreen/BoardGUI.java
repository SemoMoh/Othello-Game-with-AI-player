package com.cse.ai.othellogame.gui.gamescreen;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class BoardGUI extends AnchorPane implements Initializable {
    private final Board board;
    private boolean blackTurn;
    private int clickedIndex = -1;

    private final CountDownLatch latch;

    @FXML
    public GridPane boardPane;
    @FXML
    public AnchorPane root;

    public BoardGUI(Board board) {
        this.board = board;
        this.blackTurn = true;
        this.latch = new CountDownLatch(1);

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
    }

    public void updateBoard(boolean updateTurn) {
        for (Node node : boardPane.getChildren()) {
            Cell cell = (Cell) node;

            DISK state = board.getPos(cell.getIndex());
            if (state != DISK.HINT) {

                cell.setDisplayedImage(state);

            }
            if (updateTurn) {
                updateTurn();
            }
        }
    }

    public void updateBoardWithHints(boolean updateTurn) {
        for (Node node : boardPane.getChildren()) {
            Cell cell = (Cell) node;

            DISK state = board.getPos(cell.getIndex());
            if (state == DISK.HINT) {
                if (blackTurn) {
                    cell.setDisplayedImage(DISK.BLACK_HINT);
                } else {
                    cell.setDisplayedImage(DISK.WHITE_HINT);
                }
            } else {
                cell.setDisplayedImage(state);
            }
        }
        if (updateTurn) {
            updateTurn();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int x = 0;
        for (Node cell : boardPane.getChildren()) {
            ((Cell) cell).setIndex(x++);
            cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // Return the node that was clicked on
                    Node clickedNode = (Node) event.getSource();
                    if (clickedNode instanceof Cell clickedCell) {
                        if (clickedCell.getState() == DISK.HINT) {
                            ((Cell) clickedNode).changeState(event);
                            clickedIndex = clickedCell.getIndex();
                            latch.countDown(); // Signal that the click has been processed
                        }
                    }
                    System.out.println("Clicked on: " + clickedNode);
                }
            });
        }
        updateBoard(false);
    }


    @Override
    public Node getStyleableNode() {
        return this;
    }

    public void updateTurn() {
        blackTurn = !blackTurn;
    }

    public int getInput() {
        try {
            latch.await(); // Wait for the user to click on a cell with DISK.HINT
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        int result = clickedIndex;
        clickedIndex = -1;
        return result;
    }
}