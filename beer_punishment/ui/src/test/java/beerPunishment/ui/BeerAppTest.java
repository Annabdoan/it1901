package beerPunishment.ui;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;



/**
 * TestFX App test
 */
public class BeerAppTest extends ApplicationTest {
    private BeerController controller;
    private BeerMain beerMain;
    private Rule rule1;
    private Rule rule2;



    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Beer.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @BeforeEach
    public void setupBeerMain() {
       beerMain = new BeerMain();
       rule1 = new Rule("Banne", 4);
       rule2 = new Rule("Glemme snacks", 5);
       beerMain.addRule(rule1);
       beerMain.addRule(rule2);
       beerMain.addMember("Sara");
       beerMain.addMember("Anna");
    }



    @Test
    public void testSetUp() {
        assertNotNull(this.controller);
        assertNotNull(this.beerMain);
        assertNotNull(this.rule1);
        assertNotNull(this.rule2);
    }






}
