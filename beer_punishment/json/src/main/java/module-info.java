module beerPunishment.json {
    // requires javafx.controls;
    // requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires beerPunishment.core;
    exports beerPunishment.json;
    opens beerPunishment.json;
}
