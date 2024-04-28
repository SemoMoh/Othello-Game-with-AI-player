package com.cse.ai.othellogame;

import com.cse.ai.othellogame.gui.gamescreen.Cell;
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
        box.getChildren().add(new Cell());
        box.getChildren().add(new Cell());
        box.getChildren().add(new Cell());
    }

}