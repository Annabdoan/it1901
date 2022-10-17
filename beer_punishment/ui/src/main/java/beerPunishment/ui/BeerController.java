package beerPunishment.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import beerPunishment.json.Persistence;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;


/**
 * Top-level controller.
 */
public class BeerController {

    private BeerMain beermain;
    private Persistence persistence;


    @FXML
    private ListView<String> ruleView;
    public TextField newRuleTextInput;
    public ChoiceBox ruleChoiceBox;
    public ChoiceBox personChoiceBox;
    public ListView punishmentStatusOverview;
    public TextField addMemberText;
    private Path filePath;

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        persistence = new Persistence();
        this.setFilePath("beersystem.json");
        try {
            System.out.println(filePath.toString());
            beermain = persistence.readBeerMain(new File(filePath.toString()));
            updateMemberView();
            updatePersonChoicebox();
            updateListView();
            updateRuleChoicebox();
        } catch (IOException ioe) {
            beermain = new BeerMain();
            try {
                persistence.createFile(filePath.toString());
            } catch (IOException ioe3) {
                //Do nothing
            }
            //Do nothing
        }

    }

    /**
     * Sets the filepath to and set name to the jsonfile.
     *
     * @param filename the filename to set
     */
    private void setFilePath(String filename) {
        this.filePath = Paths.get(System.getProperty("user.home"), filename);
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
            persistence.writeBeerMain(beermain, new File(filePath.toString()));
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
                    persistence.writeBeerMain(beermain, new File(filePath.toString()));
                } catch (IOException punishMemberIOe) {
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
    public void addMember() {
        String username = addMemberText.getText();
        try {
            beermain.addMember(username);
            try {
                persistence.writeBeerMain(beermain, new File(filePath.toString()));
            } catch (IOException addMemberIoe) {
                showErrorMessage("Failed to add member");
            }
            updateMemberView();
            updatePersonChoicebox();
        } catch (IllegalArgumentException e) {
            showErrorMessage(e.getMessage());

        }
    }

    public BeerMain getBeermain() {
        return beermain;
    }


}
