package com.cse.ai.othellogame;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.backend.game.GameSystem;
import com.cse.ai.othellogame.backend.player.AIPlayer;
import com.cse.ai.othellogame.backend.player.HumanPlayer;
import com.cse.ai.othellogame.gui.gamescreen.GameScreen;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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


        GameSystem game = new GameSystem(new HumanPlayer(board, DISK.BLACK), new HumanPlayer(board, DISK.WHITE), board);
    }

}