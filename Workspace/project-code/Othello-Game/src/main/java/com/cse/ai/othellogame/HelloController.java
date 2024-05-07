package com.cse.ai.othellogame;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.backend.player.HumanPlayer;
import com.cse.ai.othellogame.gui.gamescreen.GameScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelloController {
    public VBox box;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        //box.getChildren().add(new BoardGUI(new Board()));
        //box.getChildren().add(new Cell());

        Board board = new Board();

        GameScreen g = new GameScreen(board, "H1", "H2", new HumanPlayer(board, DISK.BLACK),
                new HumanPlayer(board, DISK.WHITE));
        HelloApplication.scene.setRoot(g);
        //GameSystem game = new GameSystem(new HumanPlayer(board, DISK.BLACK), new HumanPlayer(board, DISK.WHITE), board);
    }

    public static void restart(){
        Board board = new Board();

        GameScreen g = new GameScreen(board, "H1", "H2", new HumanPlayer(board, DISK.BLACK),
                new HumanPlayer(board, DISK.WHITE));
        HelloApplication.scene.setRoot(g);
        //GameSystem game = new GameSystem(new HumanPlayer(board, DISK.BLACK), new HumanPlayer(board, DISK.WHITE), board);

    }
}