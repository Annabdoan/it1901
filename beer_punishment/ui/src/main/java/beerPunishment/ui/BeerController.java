package beerPunishment.ui;

//import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import beerPunishment.json.FileHandler;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import beerPunishment.json.Persistence;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class BeerController {

    private BeerMain beermain;
    private FileHandler filehandler;
    private Persistence persistence;
    private String filename = "Rulelist.json";

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
    private Path filePath;
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
                System.out.println("Jeg gikk inn her jeg");
                persistence.createFile(filePath.toString());
            }
            catch (IOException ioe3) {
                showErrorMessage("Noe feil skjedde i ioe3");
            }

            /*try {
                new File(filePath.toString()).createNewFile();
            }catch (IOException ioe2) {
                showErrorMessage("feilet å lage ny fil");
            }*/
            showErrorMessage("Feil ved initialize");
        }

/*
        try {
            Rule rule2 = new Rule();
            rulePersistence.createFile("test.json");
        } catch (Exception e) {
            beermain = new BeerMain();
            System.out.println("Opprettet nytt beermainobjekt");
        }
        */

    }

    private void setFilePath(String filename) {
        this.filePath = Paths.get(System.getProperty("user.home"), filename);
    }

    @FXML
    private void updateListView() {
        List<Rule> rules = beermain.getRules();
        List<String> rulesToString = new ArrayList<>();
        for (Rule rule: rules) {
            rulesToString.add(rule.toStringDisplayFormat());
        }
        ruleView.getItems().setAll(rulesToString);
    }

    private void updateRuleChoicebox() {
        List<String> ruleDescpritions = new ArrayList<>();
        try {
            for (Rule rule : beermain.getRules()) {
                ruleDescpritions.add(rule.getDescription());
            }
            ruleChoiceBox.getItems().setAll(ruleDescpritions);
        } catch (Exception e) {
            showErrorMessage("Feil updateRuleChoiceBox");
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
            persistence.writeBeerMain(beermain, new File(filePath.toString()));
            updateListView();
            updateRuleChoicebox();
        } catch (Exception e) {
            showErrorMessage("Feil i make new rule");
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
                try {
                    persistence.writeBeerMain(beermain, new File(filePath.toString()));
                }catch (IOException punishMemberIOe) {
                    showErrorMessage("Failed to punish member");
                }
            }
        }
        updateMemberView();
    }

    @FXML
    public void addMember() {
        String username = addMemberText.getText();
        try {
            beermain.addMember(username);
            try {
                persistence.writeBeerMain(beermain, new File(filePath.toString()));
            }catch (IOException addMemberIOe) {
                showErrorMessage("Failed to add member");
            }
            updateMemberView();
            updatePersonChoicebox();
        }catch (IllegalArgumentException e) {
            showErrorMessage(e.getMessage());

        }
    }

}