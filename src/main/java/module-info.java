module com.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.calculator to javafx.fxml;
    exports com.calculator;
}