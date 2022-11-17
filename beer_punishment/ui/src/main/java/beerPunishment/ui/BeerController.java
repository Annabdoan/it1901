package beerPunishment.ui;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
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

    public static final URI defaultURI = URI.create("http://localhost:8080");


    @FXML
    private ListView<String> ruleView;
    @FXML
    public TextField newRuleTextInput;
    @FXML
    public ChoiceBox ruleChoiceBox;
    @FXML
    public ChoiceBox personChoiceBox;
    @FXML
    public ChoiceBox paymentMemberChoiceBox;
    @FXML
    public ChoiceBox paymentRuleChoiceBox;
    @FXML
    public ListView punishmentStatusOverview;
    @FXML
    public TextField addMemberText;
    @FXML
    public TextField deleteMemberText;
    @FXML
    public TextField deleteRuleText;

    private IBeerMainAccess iBeerMainAccess;

    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
        iBeerMainAccess = BeerMainRemoteAccess.pingServer(defaultURI)
                ? (IBeerMainAccess) new BeerMainRemoteAccess()
                : new BeerMainLocalAccess();
        this.beermain = iBeerMainAccess.getBeermain();
        updateMemberView();
        updatePersonChoicebox();
        updateListView();
        updateRuleChoicebox();
        updatePersonChoicebox();
        updatePaymentPersonChoicebox();

    }

    /**
     * Make new rule.
     */
    @FXML
    private void addRule() {
        try {
            //Split the string in the text input in order to add a new rule.
            String[] arrOfNewRuleTextInput = newRuleTextInput.getText().split(";");
            String description = arrOfNewRuleTextInput[0];
            int value = Integer.parseInt(arrOfNewRuleTextInput[1]);
            beermain = iBeerMainAccess.addRule(beermain, description, value);
            updateListView();
            updateRuleChoicebox();
        } catch (NumberFormatException ne) {
            showErrorMessage("Feil ved å gjøre om verdi til int.");
        } catch (IllegalArgumentException iae) {
            showErrorMessage(iae.getMessage());
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            showErrorMessage("Feil form på input");
        }
    }

    /**
     * Make delete rule.
     */
    @FXML
    private void deleteRule() {
        String description = deleteRuleText.getText();
        try {
            beermain = iBeerMainAccess.deleteRule(beermain, description);
            updateRuleChoicebox();
            updateListView();
        } catch (IllegalArgumentException e) {
            showErrorMessage(e.getMessage());
        }
    }

    /**
     * Add member.
     */
    @FXML
    private void addMember() {
        String username = addMemberText.getText();
        try {
            beermain = iBeerMainAccess.addMember(beermain, username);
            updateMemberView();
            updatePersonChoicebox();
            updatePaymentPersonChoicebox();
        } catch (IllegalArgumentException iae) {
            showErrorMessage(iae.getMessage());
        }
    }

    /**
     * Delete member.
     */
    @FXML
    private void deleteMember() {
        String username = deleteMemberText.getText();
        try {
            beermain = iBeerMainAccess.deleteMember(beermain, username);
            updateMemberView();
            updatePersonChoicebox();
            updatePaymentPersonChoicebox();
        } catch (IllegalArgumentException iae) {
            showErrorMessage(iae.getMessage());
        }
    }

    /**
     * Punish a member.
     */
    @FXML
    private void punishMember() {
        String chosenRule = ruleChoiceBox.getSelectionModel().getSelectedItem().toString();
        String chosenMember = personChoiceBox.getSelectionModel().getSelectedItem().toString();
        for (Rule rule : beermain.getRules()) {
            if (rule.getDescription().equals(chosenRule)) {
                beermain = iBeerMainAccess.punishMember(beermain, chosenMember, rule.getDescription(), rule.getPunishmentValue());
            }
        }
        updateMemberView();
        updatePaymentRuleChoicebox();
    }

    /**
     * Pay violation.
     */
    @FXML
    private void deleteViolation() {
        String chosenRule = paymentRuleChoiceBox.getSelectionModel().getSelectedItem().toString();
        String chosenMember = paymentMemberChoiceBox.getSelectionModel().getSelectedItem().toString();
        for (Rule rule : beermain.getRules()) {
            if (rule.getDescription().equals(chosenRule)) {
                beermain = iBeerMainAccess.deletePunishment(beermain, chosenMember, rule.getDescription(), rule.getPunishmentValue());
            }
        }
        updateMemberView();
        updatePaymentRuleChoicebox();
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

    @FXML
    private void updateMemberView() {
        Collection<String> punishmentStatus = beermain.generatePunishmentStatusToString();
        punishmentStatusOverview.getItems().setAll(punishmentStatus);
    }

    @FXML
    private void updateRuleChoicebox() {
        Collection<String> ruleDescriptions = new ArrayList<>();
        for (Rule rule : beermain.getRules()) {
            ruleDescriptions.add(rule.getDescription());
        }
        ruleChoiceBox.getItems().setAll(ruleDescriptions);
    }

    @FXML
    private void updatePersonChoicebox() {
        try {
            personChoiceBox.getItems().setAll(beermain.getUsernames());
        } catch (Exception e) {
            showErrorMessage("Feil ved personChoicebox");
        }
    }

    /**
     * Update choicebox for rule in pay violation.
     */
    @FXML
    private void updatePaymentRuleChoicebox() {
        Collection<String> ruleDescriptions = new ArrayList<>();
        if (paymentMemberChoiceBox.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Ingen person valgt i choicebox");
        } else {
            Collection<Rule> rules = beermain.getMemberViolations(paymentMemberChoiceBox.getSelectionModel().getSelectedItem().toString());
            for (Rule rule : rules) {
                ruleDescriptions.add(rule.getDescription());
            }
            paymentRuleChoiceBox.getItems().setAll(ruleDescriptions);
        }
    }

    /**
     * Update choicebox for member in pay violation.
     */
    @FXML
    private void updatePaymentPersonChoicebox() {
        try {
            paymentMemberChoiceBox.getItems().setAll(beermain.getUsernames());
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
    private void showErrorMessage(String errorMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occured");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    /**
     * Method for returning a copy of beermain.
     *
     * @return copy of the BeerMain object in the controller
     */
    public BeerMain getBeermain() {
        BeerMain beerMainCopy = new BeerMain();
        beerMainCopy.copy(this.beermain);
        return beerMainCopy;
    }

    /**
     * Sets the name of the local json file.
     *
     * @param fileName the name to set the local json file
     */
    public void changeFileName(String fileName) {
        if (iBeerMainAccess instanceof BeerMainLocalAccess) {
            ((BeerMainLocalAccess) iBeerMainAccess).changeLocalFilename(fileName);
            this.beermain = iBeerMainAccess.getBeermain();
            updateMemberView();
            updateListView();
            updatePersonChoicebox();
            updateRuleChoicebox();
            updatePaymentRuleChoicebox();
            updatePaymentPersonChoicebox();
        } else {
            System.out.println("Not instance of LocalAccess, can therfore not change filename.");
        }
    }
}