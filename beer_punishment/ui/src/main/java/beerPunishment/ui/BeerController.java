package beerPunishment.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import beerPunishment.json.JsonHandler;
import com.google.gson.Gson;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 * Top-level controller.
 */
public class BeerController {

    private BeerMain beermain;


    @FXML
    private ListView<String> ruleView;
    public TextField newRuleTextInput;
    public ChoiceBox ruleChoiceBox;
    public ChoiceBox personChoiceBox;
    public ChoiceBox paymentMemberChoiceBox;
    public ChoiceBox paymentRuleChoiceBox;
    public ListView punishmentStatusOverview;
    public TextField addMemberText;
    public TextField deleteMemberText;
    public TextField deleteRuleText;
    private String fileName;
    private JsonHandler jsh;

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        jsh = new JsonHandler();
        try {
            fileName = "/beerPunishment.json";
            beermain = jsh.readFromJson(fileName); // Må bruke "\\" i stedet for "/" på windows
            updateMemberView();
            updatePersonChoicebox();
            updateListView();
            updateRuleChoicebox();
            updatePersonChoicebox();
            updatePaymentPersonChoicebox();
        } catch (IOException ioe) {
            beermain = new BeerMain();
        }

    }
    @FXML
    private void updateListView() {
        Collection<Rule> rules = beermain.getRules();
        Collection<String> rulesToString = new ArrayList<>();
        for (Rule rule : rules) {
            rulesToString.add(rule.toStringDisplayFormat());
        }
        ruleView.getItems().setAll(rulesToString);
    }

    private void updateRuleChoicebox() {
        Collection<String> ruleDescriptions = new ArrayList<>();
        for (Rule rule : beermain.getRules()) {
            ruleDescriptions.add(rule.getDescription());
        }
        ruleChoiceBox.getItems().setAll(ruleDescriptions);
    }

    private void updatePersonChoicebox() {
        try {
            personChoiceBox.getItems().setAll(beermain.getUsernames());
        } catch (Exception e) {
            showErrorMessage("Feil ved personChoicebox");
        }
    }

    /**
     * Show error message.
     *
     * @param errorMessage The message to show in the error
     */
    @FXML
    public void showErrorMessage(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occured");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    /**
     * Make new rule.
     */
    @FXML
    public void makeNewRule() {

        try {
            //Split the string in the text input in order to add a new rule.
            String[] arrOfNewRuleTextInput = newRuleTextInput.getText().split(";");
            Rule rule = new Rule(arrOfNewRuleTextInput[0],
                    Integer.parseInt(arrOfNewRuleTextInput[1]));
            beermain.addRule(rule);
            jsh.writeToJson(beermain, fileName);
            updateListView();
            updateRuleChoicebox();
        } catch (NumberFormatException Ne) {
            showErrorMessage("Feil ved å gjøre om verdi til int.");
        } catch (IOException | IllegalArgumentException IOe) {
            showErrorMessage(IOe.getMessage());
        }
    }
    @FXML
    public void deleteRule() {
        String description = deleteRuleText.getText();
        try {
            beermain.removeRuleUsingDescription(description);
            jsh.writeToJson(this.beermain, fileName);
            updateRuleChoicebox();
            updateListView();
        } catch (IllegalArgumentException | IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void updateMemberView() {
        Collection<String> punishmentStatus = beermain.generatePunishmentStatusToString();
        punishmentStatusOverview.getItems().setAll(punishmentStatus);
    }

    /**
     * Punish a member.
     */
    @FXML
    public void punishMember() {

        String chosenRule = ruleChoiceBox.getSelectionModel().getSelectedItem().toString();
        String chosenMember = personChoiceBox.getSelectionModel().getSelectedItem().toString();
        for (Rule rule : beermain.getRules()) {
            if (rule.getDescription().equals(chosenRule)) {
                beermain.punishMember(chosenMember, rule);
                try {
                    jsh.writeToJson(beermain, fileName);
                } catch (IOException punishMemberioe) {
                    showErrorMessage("Failed to punish member");
                }
            }
        }
        updateMemberView();
        updatePaymentRuleChoicebox();
    }

    /**
     * Add member.
     */
    @FXML
    public void addMember() {
        String username = addMemberText.getText();
        try {
            beermain.addMember(username);
            jsh.writeToJson(this.beermain, fileName);
            updateMemberView();
            updatePersonChoicebox();
            updatePaymentPersonChoicebox();
        } catch (IllegalArgumentException | IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    @FXML
    public void deleteMember() {
        String username = deleteMemberText.getText();
        try {
            beermain.deleteMember(username);
            jsh.writeToJson(this.beermain, fileName);
            updateMemberView();
            updatePersonChoicebox();
            updatePaymentPersonChoicebox();
        } catch (IllegalArgumentException | IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    @FXML
    public void payViolation() {
        String chosenRule = paymentRuleChoiceBox.getSelectionModel().getSelectedItem().toString();
        String chosenMember = paymentMemberChoiceBox.getSelectionModel().getSelectedItem().toString();
        for (Rule rule : beermain.getRules()) {
            if (rule.getDescription().equals(chosenRule)) {
                beermain.removePunishment(chosenMember, rule);
                try {
                    jsh.writeToJson(beermain, fileName);
                } catch (IOException punishMemberioe) {
                    showErrorMessage("Failed to punish member");
                }
            }
        }
        updateMemberView();
        updatePaymentRuleChoicebox();
    }

    @FXML
    private void updatePaymentRuleChoicebox() {
        Collection<String> ruleDescriptions = new ArrayList<>();
        if (paymentMemberChoiceBox.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Ingen person valgt i choicebox");
        }else {
            Collection<Rule> rules = beermain.getMemberViolations(paymentMemberChoiceBox.getSelectionModel().getSelectedItem().toString());
            for (Rule rule : rules) {
                ruleDescriptions.add(rule.getDescription());
            }
            paymentRuleChoiceBox.getItems().setAll(ruleDescriptions);
        }

    }

    private void updatePaymentPersonChoicebox() {
        try {
            paymentMemberChoiceBox.getItems().setAll(beermain.getUsernames());
        } catch (Exception e) {
            showErrorMessage("Feil ved personChoicebox");
        }
    }





    //Should consider returning a copy of the object
    public BeerMain getBeermain() {
        return this.beermain;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}