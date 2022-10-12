module beer.punishment.ui {
    requires beer.punishment.core;
    requires beer.punishment.json;

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens beerPunishment.ui to javafx.controls, javafx.graphics, javafx.fxml;
}