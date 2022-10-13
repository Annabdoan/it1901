module beerPunishment.ui {
    requires beerPunishment.core;
    requires beerPunishment.json;

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens beerPunishment.ui to javafx.controls, javafx.graphics, javafx.fxml;
}