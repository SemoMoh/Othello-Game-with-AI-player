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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Cell extends StackPane implements Initializable {
    private static Image blackDisc;
    private static Image whiteDisc;
    private static Image blackHint;
    private static Image whiteHint;
    @FXML
    StackPane root;
    private MediaPlayer mediaPlayer = new MediaPlayer(
            new Media(
                    Objects.requireNonNull(Cell.class.getResource("/sound effects/cell sound.mp3")).toString()
            )
    );

    private ImageView displayedImage;
    private int index;

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
        removeMarkLastPlayed();

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
            // play sound
            playSound();

            if (displayedImage.getImage().equals(blackHint)) {
                setDisplayedImage(DISK.BLACK);
                return true;
            } else if (displayedImage.getImage().equals((whiteHint))) {
                setDisplayedImage(DISK.WHITE);
                return true;
            }
            markLastPlayed();
        }

        return false;
    }

    public void playSound() {
        mediaPlayer.setVolume(0.2);
        mediaPlayer.stop();
        mediaPlayer.play();
    }

    public void markLastPlayed() {
        // Create horizontal line for the cross
        Line horizontalLine = new Line(0, 10, 0, -10);
        horizontalLine.setStroke(Color.ORANGERED); // Set the color of the line
        horizontalLine.setStrokeWidth(2); // Set the width of the line

        // Create vertical line for the cross
        Line verticalLine = new Line(-10, 0, 10, 0);
        verticalLine.setStroke(Color.ORANGERED); // Set the color of the line
        verticalLine.setStrokeWidth(2); // Set the width of the line

        // Add the lines to the StackPane
        getChildren().addAll(horizontalLine, verticalLine);
    }


    private void removeMarkLastPlayed() {
        // Find and remove the lines representing the cross
        getChildren().removeIf(node -> node instanceof Line);
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


        // hover effect
        root.setOnMouseEntered(event -> {
            if (getState() == DISK.HINT || getState() == DISK.BLACK_HINT || getState() == DISK.WHITE_HINT) {
                root.setStyle("-fx-background-color:  #009b4b; -fx-border-color:  transparent; " +
                        "-fx-border-radius: 5; -fx-background-radius: 5");
            } else {
                root.setStyle("-fx-background-color:  #01b356; -fx-border-color:  transparent; " +
                        "-fx-border-radius: 5; -fx-background-radius: 5");
            }
        });
        root.setOnMouseExited(event -> {
            root.setStyle("-fx-background-color:  #01b356; -fx-border-color:  transparent; " +
                    "-fx-border-radius: 5; -fx-background-radius: 5");
        });


        // Set StackPane's preferred dimensions and alignment
        root.setPrefSize(71.63, 71.63);
        StackPane.setAlignment(displayedImage, javafx.geometry.Pos.CENTER);
    }

    @Override
    public Node getStyleableNode() {
        return this;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int x) {
        index = x;
    }

    @Override
    public String toString() {
        return Integer.toString(index);
    }
}
