package beerPunishment.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public ListView punishmentStatusOverview;
    public TextField addMemberText;
    public TextField deleteMemberText;
    public Button deleteMemberButton;
    private String fileName;
    private JsonHandler jsh;

    /**
     * Initialize.
     */
    @FXML
    public void initialize() throws IOException {
        jsh = new JsonHandler();
        try {
            fileName = "/beerPunishment.json";
            beermain = jsh.readFromJson(fileName); // Må bruke "\\" i stedet for "/" på windows
            updateMemberView();
            updatePersonChoicebox();
            updateListView();
            updateRuleChoicebox();
        } catch (IOException ioe) {
            beermain = new BeerMain();
        }

    }
    @FXML
    private void updateListView() {
        List<Rule> rules = beermain.getRules();
        List<String> rulesToString = new ArrayList<>();
        for (Rule rule : rules) {
            rulesToString.add(rule.toStringDisplayFormat());
        }
        ruleView.getItems().setAll(rulesToString);
    }

    private void updateRuleChoicebox() {
        List<String> ruleDescriptions = new ArrayList<>();
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
    private void showErrorMessage(String errorMessage) {
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
        } catch (IOException IOe) {
            showErrorMessage(IOe.getMessage());
        }



    }

    private void updateMemberView() {
        List<String> punishmentStatus = beermain.generatePunishmentStatusToString();
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


    }

    /**
     * Add member.
     */
    @FXML
    public void addMember() throws IOException {
        String username = addMemberText.getText();
        try {
            beermain.addMember(username);
            jsh.writeToJson(this.beermain, fileName);
            updateMemberView();
            updatePersonChoicebox();
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
        } catch (IllegalArgumentException | IOException e) {
            showErrorMessage(e.getMessage());
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