package com.cse.ai.othellogame.gui.gamescreen;

import com.cse.ai.othellogame.backend.game.DISK;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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

/**
 * Represents a cell in the game board Graphical User Interface.
 */
public class Cell extends StackPane implements Initializable {
    // Load the disc images for the cells
    private static final Image blackDisc = new Image(Objects.requireNonNull(Cell.class.getResourceAsStream("/images/board/cells/black.png")));
    private static final Image whiteDisc = new Image(Objects.requireNonNull(Cell.class.getResourceAsStream("/images/board/cells/white.png")));
    private static final Image blackHint = new Image(Objects.requireNonNull(Cell.class.getResourceAsStream("/images/board/cells/black-hint.png")));
    private static final Image whiteHint = new Image(Objects.requireNonNull(Cell.class.getResourceAsStream("/images/board/cells/white-hint.png")));

    // Load the sound effect for the cell
    private final MediaPlayer mediaPlayer = new MediaPlayer(
            new Media(
                    Objects.requireNonNull(Cell.class.getResource("/sound effects/cell sound.mp3")).toString()
            )
    );

    @FXML
    StackPane root;

    private ImageView displayedImage;
    private int index;

    /**
     * Constructs a cell object.
     */
    public Cell() {
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

    /**
     * Sets the displayed image based on the disk color.
     *
     * @param color The color of the disk to be displayed.
     */
    public void setDisplayedImage(DISK color) {
        // Remove the cross if it exists
        removeMarkLastPlayed();

        // Set the displayed image based on the color
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

    /**
     * Gets the state of the cell based on the image it shows.
     *
     * @return The state of the cell.
     */
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

    /**
     * Handles the state change of the cell.
     * <p>
     * The change happens when the cell is in a hint state and the user clicks on it.
     * </p>
     *
     * @param ignoredEvent The mouse event (ignored).
     * @return {@code true} if the state change was successful, {@code false} otherwise.
     */
    public boolean changeState(MouseEvent ignoredEvent) {
        DISK state = getState();
        if (state == DISK.HINT) {
            // play sound of changing
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

    /**
     * Plays the cell sound.
     */
    public void playSound() {
        // Set the volume of the sound
        mediaPlayer.setVolume(0.2);
        // Stop if it was still playing, then play the sound
        mediaPlayer.stop();
        mediaPlayer.play();
    }

    /**
     * Marks the cell as the last played by drawing a small red cross in the middle of the cell.
     */
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

    /**
     * Removes the mark indicating the cell as the last played.
     */
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

        // hover effect on cells with a hint.
        root.setOnMouseEntered(event -> {
            if (getState() == DISK.HINT || getState() == DISK.BLACK_HINT || getState() == DISK.WHITE_HINT) {
                root.setStyle("-fx-background-color:  #009b4b; -fx-border-color:  transparent; " +
                        "-fx-border-radius: 5; -fx-background-radius: 5");
            } else {
                root.setStyle("-fx-background-color:  #01b356; -fx-border-color:  transparent; " +
                        "-fx-border-radius: 5; -fx-background-radius: 5");
            }
        });
        root.setOnMouseExited(event -> root.setStyle("-fx-background-color:  #01b356; -fx-border-color:  transparent; " +
                "-fx-border-radius: 5; -fx-background-radius: 5"));


        // Set StackPane's preferred dimensions and alignment
        root.setPrefSize(71.63, 71.63);
        StackPane.setAlignment(displayedImage, Pos.CENTER);
    }

    @Override
    public Node getStyleableNode() {
        return this;
    }

    /**
     * Gets the index of the cell.
     *
     * @return The index of the cell.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the index of the cell.
     *
     * @param index The index to be set.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return The index of the cell as a string.
     */
    @Override
    public String toString() {
        return Integer.toString(index);
    }
}
