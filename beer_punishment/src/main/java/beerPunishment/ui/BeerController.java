package beerPunishment.ui;

import java.util.ArrayList;
import java.util.List;

import beerPunishment.json.FileHandler;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;


public class BeerController {

    private BeerMain beermain;
    private FileHandler filehandler;
    private String filename = "Rulelist";

    @FXML
    private ListView<String> ruleView;
    public TextField newRuleTextInput;
    public Button newRuleButton;
    public ChoiceBox ruleChoiceBox;
    public ChoiceBox personChoiceBox;
    public Button addMemberButton;
    public Button punishButton;
    public ListView punishmentStatusOverview;
    public TextField addMemberText;
    @FXML
    public void initialize() {
        beermain = new BeerMain();
        filehandler = new FileHandler();
        updateListView();
        updateChoicebox();
    }

    @FXML
    private void updateListView() {
        List<String> rulestostring = new ArrayList<>();
        try {
            List<Rule> rules = filehandler.readRules(filename);
            for (Rule rule : rules) {
                rulestostring.add(rule.toString());
            }
            ruleView.getItems().setAll(rulestostring);
        } catch (Exception e) {
            showErrorMessage("Feil format på regel. Regel;antall øl");
        }
    }

    private void updateChoicebox() {
        List<String> ruleDescpritions = new ArrayList<>();
        try {
            List<Rule> rules = filehandler.readRules(filename);
            for (Rule rule : rules) {
                ruleDescpritions.add(rule.getDescription());
            }
            ruleChoiceBox.getItems().setAll(ruleDescpritions);
        } catch (Exception e) {
            showErrorMessage("Feil ved lesing fra fil");
        }
    }
    @FXML
    private void showErrorMessage(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occured");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @FXML
    public void makeNewRule() {
        try {
            //Split the string in the text input in order to add a new rule.
            String[] arrOfNewRuleTextInput = newRuleTextInput.getText().split(";");
            Rule rule = new Rule(arrOfNewRuleTextInput[0], Integer.valueOf(arrOfNewRuleTextInput[1]));
            filehandler.writeRule(filename,rule);
            updateListView();
            updateChoicebox();
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }

    }

    @FXML
    public void punishMember() {

    }

    @FXML
    public void addMember() {

    }

}
