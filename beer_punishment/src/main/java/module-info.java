module beerPunishment {
    requires javafx.controls;
    requires javafx.fxml;

    opens beerPunishment to javafx.graphics, javafx.fxml;
}
