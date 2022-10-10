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
import beerPunishment.json.RulePersistence1;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class BeerController {

    private BeerMain beermain;
    private FileHandler filehandler;
    private String filename = "Rulelist.json";

    @FXML
    private ListView<String> ruleView;

    @FXML
    String userRuleListPath;

    @FXML
    String sampleRuleListResource;



  /*  @FXML
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

    }*/
    private RulePersistence rulePersistence = new RulePersistence();

    private void initialize() throws IOException {
        //setter opp data
        Reader reader = null;
        //try to read file from home folder first
        if (userRuleListPath != null) {
            try {
                reader = new FileReader(Paths.get(System.getProperty("user.home"), userRuleListPath).toFile(), StandardCharsets.UTF_8);
            } catch (IOException ioex) {
                System.err.println("Fant ingen" + userRuleListPath + "på hjemmeområdet");
            }
        }
        if (reader == null && sampleRuleListResource != null) {
        /*    //try sample rule list resources instead
            URL url = getClass().getResource(sampleRuleListResource);
            if (url != null) {
                try {
                    reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    System.err.println("Kunne ikke lese innebygget" + sampleRuleListResource);
                }
            } else {
                System.err.println("Fant ikke innebygget" + sampleRuleListResource);
            }*/
        }
        if (reader == null) {
            //use embedded string
           // reader = new StringReader(userRuleListPath);
            rulePersistence.setSaveFile(filename);
            Rule rule = new Rule("Komme sent",2);
            rulePersistence.saveRule(rule);
        }
    }
/*
    void autoSaveRuleList() {
        if (userRuleListPath != null){
            Path path = Paths.get(System.getProperty("user.home").userRuleListPath);
            try (Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8)) {
                rulePersistence.writeRuleList(ruleList, writer);
            } catch (IOException e) {
                System.err.println("Fikk ikke skrevet til rulelist.json på hjemmeområdet");
            }
        }

    }*/
}
