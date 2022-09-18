package beerPunishment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class BeerApp extends Application {



    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ølstraff");
        primaryStage.setScene(new Scene(FXMLLoader.load(BeerApp.class.getResource("Beer.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        BeerApp.launch(args);
    }
}