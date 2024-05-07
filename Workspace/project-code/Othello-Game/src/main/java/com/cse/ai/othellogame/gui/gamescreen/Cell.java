package com.cse.ai.othellogame.gui.gamescreen;

import com.cse.ai.othellogame.backend.game.DISK;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Cell extends StackPane implements Initializable {
    @FXML
    StackPane root;
    private static Image blackDisc;
    private static Image whiteDisc;
    private static Image blackHint;
    private static Image whiteHint;
    private ImageView displayedImage;
    private int index;
    private DISK state;

    public Cell(int index) {
        this();
        this.index = index;
    }

    public Cell() {
        // load all images if not loaded
        if (blackDisc == null || whiteDisc == null || blackHint == null || whiteHint == null) {
            blackDisc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/board/cells/black.png")));
            whiteDisc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/board/cells/white.png")));
            whiteHint = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/board/cells/white-hint.png")));
            blackHint = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/board/cells/black-hint.png")));
        }

        //load fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "cell-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    public void setDisplayedImage(DISK color) {
        switch (color) {
            case EMPTY:
                this.displayedImage.setImage(null);
                break;
            case BLACK_HINT:
                this.displayedImage.setImage(blackHint);
                break;
            case WHITE_HINT:
                this.displayedImage.setImage(whiteHint);
                break;
            case BLACK:
                this.displayedImage.setImage(blackDisc);
                break;
            case WHITE:
                this.displayedImage.setImage(whiteDisc);
                break;
        }
        this.state = color;
    }


    public DISK getState() {
        if (displayedImage.getImage() == null) {
            return DISK.EMPTY;
        } else if (displayedImage.getImage().equals(blackDisc)) {
            return DISK.BLACK;
        } else if (displayedImage.getImage().equals(whiteDisc)) {
            return DISK.WHITE;
        } else {
            return DISK.HINT;
        }
    }

    public boolean changeState(MouseEvent event) {
        DISK state = getState();
        if (state == DISK.HINT) {
            if (displayedImage.getImage().equals(blackHint)) {
                setDisplayedImage(DISK.BLACK);
                return true;
            } else if (displayedImage.getImage().equals((whiteHint))) {
                setDisplayedImage(DISK.WHITE);
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Construct and add the displayed image object into the StackPane
        displayedImage = new ImageView(blackHint);
        root.getChildren().add(displayedImage);

        // Set the fitWidth and fitHeight properties to fill the StackPane
        displayedImage.setFitWidth(71.63);
        displayedImage.setFitHeight(71.63);

        // Set preserveRatio to false to prevent image distortion
        displayedImage.setPreserveRatio(false);

        // Set StackPane's preferred dimensions and alignment
        root.setPrefSize(71.63, 71.63);
        StackPane.setAlignment(displayedImage, javafx.geometry.Pos.CENTER);
    }

    @Override
    public Node getStyleableNode() {
        return this;
    }

    public void setIndex(int x) {
        index = x;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return Integer.toString(index);
    }
}
