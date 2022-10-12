package beerPunishment.ui;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import beerPunishment.json.FileHandler;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import beerPunishment.json.RulePersistence;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class BeerController {

    private BeerMain beermain;
    private FileHandler filehandler;
    private RulePersistence rulePersistence;
    private String filename = "Rulelist.json";

    @FXML
    private ListView<String> ruleView;

    @FXML
    String userRuleListPath;

    @FXML
    String sampleRuleListResource;


    @FXML
    public void initialize() {
        beermain = new BeerMain();
        filehandler = new FileHandler();
        rulePersistence = new RulePersistence();

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
