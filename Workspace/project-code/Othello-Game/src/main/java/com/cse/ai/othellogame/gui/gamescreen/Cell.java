package com.cse.ai.othellogame.gui.gamescreen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


    public Cell() {
        // load all images if not loaded
        if (blackDisc == null || whiteDisc == null || blackHint == null || whiteHint == null) {
            blackDisc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/board/cells/black.png")));
            whiteDisc = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/board/cells/white.png")));
            whiteHint = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/board/cells/white-hint.png")));
            blackHint = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/board/cells/black-hint.png")));
/*            blackDisc = loadSVGImage("/images/board/cells/black.svg");
            whiteDisc = loadSVGImage("/images/board/cells/white.svg");
            whiteHint = loadSVGImage("/images/board/cells/white-hint.svg");
            blackHint = loadSVGImage("/images/board/cells/black-hint.svg");*/

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

    /**
     * @param color
     * @param isHint
     */
    public void setDisplayedImage(char color, boolean isHint) {
        if (color == 'E') {
            this.displayedImage.setImage(null);
        }

        if (isHint) {
            if (color == 'B') {
                this.displayedImage.setImage(blackHint);
            } else {
                this.displayedImage.setImage(whiteHint);
            }
        } else {
            if (color == 'B') {
                this.displayedImage.setImage(blackDisc);
            } else {
                this.displayedImage.setImage(whiteDisc);
            }
        }
    }

    /**
     * @return
     */
    public char getState() {
        if (displayedImage.getImage().equals(blackDisc)) {
            return 'B';
        } else if (displayedImage.getImage().equals(whiteDisc)) {
            return 'W';
        } else if (displayedImage.getImage() == null) {
            return 'E';
        } else {
            return 'H';
        }
    }

    public void changeState() {
        char state = getState();
        if (state == 'H') {
            if (displayedImage.getImage().equals(blackHint)) {
                setDisplayedImage('B', false);
            } else if (displayedImage.getImage().equals((whiteHint))) {
                setDisplayedImage('W', false);
            }
        }
        System.out.println("Pressed");
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

/*    private Image loadSVGImage(String path) {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            SVGImage svgImage = new SVGImage();

            return new Image(inputStream);
        } catch (FileNotFoundException e) {
            System.err.println("SVG file not found: " + path);
            e.printStackTrace();
            return null;
        }
    }*/

}
