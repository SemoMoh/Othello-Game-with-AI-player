module com.cse.ai.othellogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cse.ai.othellogame to javafx.fxml;
    exports com.cse.ai.othellogame;
}