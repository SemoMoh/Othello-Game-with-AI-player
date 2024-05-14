package com.cse.ai.othellogame.gui.gamescreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Represents a custom dialog box in the game screen.
 */
public class DialogBox extends VBox implements Initializable {
    private final GameScreen gameScreen;
    private final String titleStr;

    @FXML
    public Label title;

    @FXML
    public Button confirmButton;

    @FXML
    public Button cancelButton;

    /**
     * Constructs a new dialog box.
     *
     * @param title      The title of the dialog box.
     * @param gameScreen The game screen associated with the dialog box.
     */
    public DialogBox(String title, GameScreen gameScreen){
        this.titleStr = title;
        this.gameScreen = gameScreen;

        //load fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "dialog-box-view.fxml"));
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
        title.setText(this.titleStr);

        // hover effect
        // for `confirm button`
        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle(
                "-fx-background-color:  #B00000;" +
                        "-fx-background-radius: 12px;"
        ));

        confirmButton.setOnMouseExited(e -> confirmButton.setStyle(
                "-fx-background-color:  #860000;" +
                        "-fx-background-radius: 12px;"
        ));

        // for `cancel button`
        cancelButton.setOnMouseEntered(e -> cancelButton.setStyle(
                "-fx-background-color:   #212121;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 12px;"
        ));

        cancelButton.setOnMouseExited(e -> cancelButton.setStyle(
                "-fx-background-color:   #0C0C0C;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3px;" +
                        "-fx-border-radius: 12px;"
        ));
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    /**
     * Handles the action when the `confirm button` is pressed, and return to the game screen the response.
     */
    public void confirmPressed(){
        gameScreen.setDialogBoxResponse(true, titleStr);
    }

    /**
     * Handles the action when the `cancel button` is pressed, and return to the game screen the response.
     */
    public void cancelPressed(){
        gameScreen.setDialogBoxResponse(false, titleStr);
    }
}
