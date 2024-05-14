package com.cse.ai.othellogame.gui.resultscreen;

import com.cse.ai.othellogame.MainGUI;
import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.player.Player;
import com.cse.ai.othellogame.gui.gamescreen.BoardGUI;
import com.cse.ai.othellogame.gui.mainmenu.MainMenu;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultScreen extends Pane implements Initializable {
    private final Board board;
    private final BoardGUI boardGUI;

    public ResultScreen(Board board, String playerBlackName, String playerWhiteName, BoardGUI boardGUI, Player playerBlack, Player playerWhite) {
        this.board = board;
        this.boardGUI = boardGUI;

        //load fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "result-screen-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    /**
     * This method is used to close the game application.
     * When the user clicks the close button, the game will close.
     */
    public void closeApp(){
        MainGUI.endGame();
    }

    /**
     * This method is used to go back to the main menu.
     * When the user clicks the main menu button, the game will go back to the main menu.
     */
    public void mainMenu(){
        MainGUI.mainMenu();
    }

    /**
     * This method is used to restart the game.
     * When the user clicks the restart button, the game will restart.
     */
    public void restartGame(){
        MainMenu.restartTheGame();
    }
}
