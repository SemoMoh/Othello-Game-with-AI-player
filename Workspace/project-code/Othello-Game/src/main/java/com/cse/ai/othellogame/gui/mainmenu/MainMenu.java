package com.cse.ai.othellogame.gui.mainmenu;

import com.cse.ai.othellogame.backend.player.HumanPlayer;
import com.cse.ai.othellogame.gui.gamescreen.GameScreen;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import com.cse.ai.othellogame.HelloApplication;
import com.cse.ai.othellogame.HelloController;
import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.backend.player.AIPlayer;
import com.cse.ai.othellogame.backend.player.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;



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




    public MainMenu(){

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



    public void changeWhitePlayerType(){
        if (whitePlayerType.getText().equals( "AI"))
            whitePlayerType.setText("Human");
        else
            whitePlayerType.setText("AI");
    }
    public void changeBlackPlayerType(){
        if (blackPlayerType.getText().equals( "AI"))
            blackPlayerType.setText("Human");
        else
            blackPlayerType.setText("AI");
    }

    public void changeWhiteDifficulty(){
        if (whiteDifficulty.getText().equals("Difficulty: Easy"))
            whiteDifficulty.setText("Difficulty: Medium");
        else if (whiteDifficulty.getText().equals("Difficulty: Medium")) {
            whiteDifficulty.setText("Difficulty: Hard");
        }
        else
            whiteDifficulty.setText("Difficulty: Easy");
    }

    public void changeBlackDifficulty(){
        if (blackDifficulty.getText().equals("Difficulty: Easy"))
            blackDifficulty.setText("Difficulty: Medium");
        else if (blackDifficulty.getText().equals("Difficulty: Medium")) {
            blackDifficulty.setText("Difficulty: Hard");
        }
        else
            blackDifficulty.setText("Difficulty: Easy");
    }



    public String getBlackPlayerName() {
        if (!blackPlayerName.getText().equals("")) {
            return blackPlayerName.getText();
        }
        return "Snoop Dog";
    }

    public String getWhitePlayerName() {
        if (!whitePlayerName.getText().equals("")) {
            return whitePlayerName.getText();
        }
        return "Taylor Swift";
    }


    public void startTheGame(){
        System.out.println("K30");

        //HelloController.restart();

        Board board = new Board();

        Player blackPlayer;
        Player whitePlayer;

        String blackName;
        if (isAIPlayer(blackPlayerType)) {
            blackPlayer = new AIPlayer(board , DISK.BLACK , getDifficultyLevel(blackDifficulty.getText()));
            blackName = "Nigga AI";
        } else {
            blackPlayer  = new HumanPlayer(board , DISK.BLACK );
            blackName = getBlackPlayerName();
        }

        String whiteName;
        if (isAIPlayer(whitePlayerType)) {
            whitePlayer = new AIPlayer(board , DISK.WHITE , getDifficultyLevel(whiteDifficulty.getText()));
            whiteName = " Charlie AI";
        } else {
            whitePlayer = new HumanPlayer(board , DISK.WHITE );
            whiteName=getWhitePlayerName();
        }

        GameScreen g = new GameScreen(board,blackName,whiteName,blackPlayer,whitePlayer);
        HelloApplication.scene.setRoot(g);


    }


    public static void restartTheGame(){
        mainMenu.startTheGame();
    }


    private boolean isAIPlayer(Button playerTypeButton) {
        return playerTypeButton.getText().equals("AI");
    }

    private int getDifficultyLevel(String buttonText) {
        switch (buttonText) {
            case "Difficulty: Easy":
                return 1;
            case "Difficulty: Medium":
                return 2;
            case "Difficulty: Hard":
                return 6;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + buttonText+1);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //root.getChildren().add(this);
        // Bind visibility of text fields and buttons based on player type
        blackPlayerName.visibleProperty().bind(Bindings.createBooleanBinding(() -> blackPlayerType.getText().equals("Human"), blackPlayerType.textProperty()));
        whitePlayerName.visibleProperty().bind(Bindings.createBooleanBinding(() -> whitePlayerType.getText().equals("Human"), whitePlayerType.textProperty()));
        whiteDifficulty.visibleProperty().bind(Bindings.createBooleanBinding(() -> whitePlayerType.getText().equals("AI"), whitePlayerType.textProperty()));
        blackDifficulty.visibleProperty().bind(Bindings.createBooleanBinding(() -> blackPlayerType.getText().equals("AI"), blackPlayerType.textProperty()));
    }
}
