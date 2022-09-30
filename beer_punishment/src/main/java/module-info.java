module beerPunishment {
    requires javafx.controls;
    requires javafx.fxml;

    opens beerPunishment.ui to javafx.fxml, javafx.graphics;

}
