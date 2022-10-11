module beerPunishment.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires beerPunishment.core;
    requires beerPunishment.json;

    opens beerPunishment.ui to javafx.fxml, javafx.graphics;
    exports beerPunishment.ui;
}
