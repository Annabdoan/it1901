module beerPunishment.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens beerPunishment.ui to javafx.fxml, javafx.graphics;

}
