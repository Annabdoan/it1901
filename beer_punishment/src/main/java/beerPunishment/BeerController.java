package beerPunishment;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;

public class BeerController {

    private BeerMain beermain;
    private FileHandler filehandler;
    private String filename = "Rulelist";

    @FXML
    private ListView<String> ruleView;


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
}
