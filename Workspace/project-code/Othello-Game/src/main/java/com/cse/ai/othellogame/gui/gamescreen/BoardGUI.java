package com.cse.ai.othellogame.gui.gamescreen;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.backend.game.GameSystem;
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

public class BoardGUI extends AnchorPane implements Initializable {
    private final Board board;
    private int clickedIndex = -1;


    @FXML
    public GridPane boardPane;
    @FXML
    public AnchorPane root;
    private GameSystem gameSystem;

    public BoardGUI(Board board) {
        this.board = board;

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
        updateBoard(false);
    }

    public void updateBoard(boolean updateTurn) {
        for (Node node : boardPane.getChildren()) {
            Cell cell = (Cell) node;

            DISK state = board.getPos(cell.getIndex());
            if (state != DISK.HINT && state != DISK.BLACK_HINT && state != DISK.WHITE_HINT) {
                cell.setDisplayedImage(state);
            } else{
                cell.setDisplayedImage(DISK.EMPTY);
            }
        }
    }

    public void updateBoardWithHints(boolean updateTurn) {
        for (Node node : boardPane.getChildren()) {
            Cell cell = (Cell) node;

            DISK state = board.getPos(cell.getIndex());
            if (state == DISK.HINT) {
                if (gameSystem.isBlackTurn()){
                    cell.setDisplayedImage(DISK.BLACK_HINT);
                } else {
                    cell.setDisplayedImage(DISK.WHITE_HINT);
                }
            } else {
                cell.setDisplayedImage(state);
            }
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
                        boolean validInput = clickedCell.changeState(event);
                        if (validInput) {
                            clickedIndex = clickedCell.getIndex();
                            DISK d = gameSystem.isBlackTurn() ? DISK.BLACK : DISK.WHITE;
                            //System.out.println(board);
                            /*Platform.runLater(() -> {
                                GameScreen.gameScreen.makeHumanMove();
                            });*/
                            gameSystem.setHumanInput(clickedIndex);
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



    public int getInput() {

        int result = clickedIndex;
        clickedIndex = -1;
        return result;
    }

    public void setGameSystem(GameSystem gameSystem) {
        this.gameSystem = gameSystem;
    }
}
