module com.cse.ai.othellogame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.girod.javafx.svgimage;
    requires javafx.media;


    opens com.cse.ai.othellogame to javafx.fxml;
    opens com.cse.ai.othellogame.gui.gamescreen to javafx.fxml;
    exports com.cse.ai.othellogame;
    exports com.cse.ai.othellogame.gui.mainmenu to javafx.fxml;
    opens com.cse.ai.othellogame.gui.mainmenu to javafx.fxml;
    opens com.cse.ai.othellogame.gui.resultscreen to javafx.fxml;
}