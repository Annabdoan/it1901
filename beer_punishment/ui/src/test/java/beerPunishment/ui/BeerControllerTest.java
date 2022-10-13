package beerPunishment.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import beerPunishment.ui.BeerApp;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class BeerControllerTest extends ApplicationTest {

    private ListView<String> ruleView;
    public TextField newRuleTextInput;
    public Button newRuleButton;
    public ChoiceBox ruleChoiceBox;
    public ChoiceBox personChoiceBox;
    public Button addMemberButton;
    public Button punishButton;
    public ListView punishmentStatusOverview;
    public TextField addMemberText;
    private BeerMain beerMain;


    /*@BeforeEach
    public void initFields() {
        newRuleButton = lookup("#newRuleButton").query();
        ruleChoiceBox = lookup("#ruleChoiceBox").query();
        personChoiceBox = lookup("#personChoiceBox").query();
        addMemberButton = lookup("#addMemberButton").query();
        punishButton = lookup("#punishButton").query();
        punishmentStatusOverview = lookup("#punishmentStatusOverview").query();
        addMemberText = lookup("#addMemberText").query();
    }*/

    /*@Override
    public void start(final Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        BeerController controller = new BeerController();
        loader.setController(controller);
        //controller.setDataAccess(dataAccess); Usikker på hva den gjør
        loader.setLocation(BeerApp.class.getResource("views/CreateUser.fxml"));
        final Parent parent = loader.load();
        //controller.loadUserAndAccount(); Har ikke gjort ferdig lesing fra fil
        stage.setScene(new Scene(parent));
        stage.show();
    }*/

    @BeforeEach
    public void setUp() {
        beerMain = new BeerMain();
        Rule rule1 = new Rule("Be negative", 2);
        Rule rule2 = new Rule("Arrive late to meeting", 3);
        Rule rule3 = new Rule("Using cellphone during meeting", 1);
        beerMain.addRule(rule1);
        beerMain.addRule(rule2);
        beerMain.addRule(rule3);
    }

   /* @Test
    public void testCorrectlyLoaded() {
        assertTrue(newRuleButton.isVisible());
        assertTrue(ruleChoiceBox.isVisible());
        assertTrue(personChoiceBox.isVisible());
        assertTrue(addMemberButton.isVisible());
        assertTrue(punishButton.isVisible());
        assertTrue(punishmentStatusOverview.isVisible());
        assertTrue(addMemberText.isVisible());
    }*/

    @Test
    public void basicTest() {
        assertTrue(true);
    }

}
