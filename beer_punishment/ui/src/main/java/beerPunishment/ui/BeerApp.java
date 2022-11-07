package beerPunishment.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App.
 */
public final class BeerApp extends Application {


    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ølstraff");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Beer.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}