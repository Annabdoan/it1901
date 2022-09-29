module beerPunishment {
    requires javafx.controls;
    requires javafx.fxml;

    opens beerPunishment to javafx.graphics, javafx.fxml;
    opens beerPunishment.ui to javafx.fxml, javafx.graphics;
    opens beerPunishment.core to javafx.fxml, javafx.graphics;
    opens beerPunishment.json to javafx.fxml, javafx.graphics;
}
