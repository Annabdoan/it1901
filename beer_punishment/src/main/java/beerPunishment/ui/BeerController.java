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

    private Rule rule;

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
        try {
            List<Rule> rulesFromTxt = filehandler.readRules(filename);
            for (Rule rule: rulesFromTxt) {
                beermain.addRule(rule);
            }
        }catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
        updateListView();
        updateRuleChoicebox();
        updateMemberView();
        updatePersonChoicebox();
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

    private void updateRuleChoicebox() {
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

    private void updatePersonChoicebox() {
        try {
            personChoiceBox.getItems().setAll(beermain.getUsernames());
        } catch (Exception e) {
            showErrorMessage("Feil ved personChoicebox");
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
            beermain.addRule(rule);
            filehandler.writeRule(filename,rule);
            updateListView();
            updateRuleChoicebox();
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }

    }

    private void updateMemberView() {
        List<String> punishmentStatus = beermain.generatePunishmentStatusToString();
        punishmentStatusOverview.getItems().setAll(punishmentStatus);
    }

    @FXML
    public void punishMember() {
        String chosenRule = ruleChoiceBox.getSelectionModel().getSelectedItem().toString();
        String chosenMember = personChoiceBox.getSelectionModel().getSelectedItem().toString();
        for (Rule rule: beermain.getRules()) {
            if (rule.getDescription().equals(chosenRule)) {
                beermain.punishMember(chosenMember,rule);
            }
        }
        updateMemberView();
    }

    @FXML
    public void addMember() {
        String username = addMemberText.getText();
        try {
            beermain.addMember(username);
            updateMemberView();
            updatePersonChoicebox();
        }catch (IllegalArgumentException e) {
            showErrorMessage(e.getMessage());

        }
    }

}
