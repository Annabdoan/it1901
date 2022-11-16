module beerPunishment.ui {
    requires beerPunishment.core;
    requires beerPunishment.json;

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    requires java.net.http;
    //requires spring.web;



    opens beerPunishment.ui to javafx.controls, javafx.graphics, javafx.fxml, com.google.gson;
}