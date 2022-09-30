package beerPunishment.ui;

import java.util.ArrayList;
import java.util.List;

import beerPunishment.json.FileHandler;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class BeerController {

    private BeerMain beermain;
    private FileHandler filehandler;
    private String filename = "Rulelist";

    @FXML
    private ListView<String> ruleView;
    public TextField newRuleTextInput;
    public Button newRuleButton;


    @FXML
    public void initialize() {
        beermain = new BeerMain();
        filehandler = new FileHandler();
        List<String> rulestostring = new ArrayList<>();
        try {
            List<Rule> rules = filehandler.readRules(filename);
            for (Rule rule : rules) {
                rulestostring.add(rule.toString());
            }
            ruleView.getItems().setAll(rulestostring);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid filename");
        }

    }

    @FXML
    public void makeNewRule() {

    }
}
