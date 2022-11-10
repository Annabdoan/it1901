package beerPunishment.ui;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import beerPunishment.json.JsonHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class BeerMainLocalAccess implements IBeerMainAccess {

    private BeerMain beerMain;
    private JsonHandler jsh;
    private Rule rule;

    private void writeBeerMainToJson(BeerMain beerMain) throws IOException {
        try {
            jsh.writeToJson(beerMain, "/beerPunishment.json");

        }catch (IOException ioe){
            throw new IOException("Feil ved skriving til fil.");
        }
    }

    @Override
    public BeerMain getBeermain() {
        jsh = new JsonHandler();
        try {
            beerMain = jsh.readFromJson("/beerPunishment.json");
            return beerMain;
        }catch (IOException ioe){
            return new BeerMain();
        }
    }

    @Override
    public BeerMain addRule(BeerMain beerMain, String description, int value) {
        try {
            rule = new Rule(description, value);
            BeerMain beerMain2 = getBeermain();
            beerMain2.addRule(rule);
            writeBeerMainToJson(beerMain2);
            return beerMain2;
        }catch (IOException ioe) {
            System.out.println("Could not add rule");
            return beerMain;
        }

    }

    @Override
    public BeerMain addMember(BeerMain beerMain, String name) {
        try{
            BeerMain beerMain2 = getBeermain();
            beerMain2.addMember(name);
            writeBeerMainToJson(beerMain2);
            return beerMain2;
        }catch(IOException ioe){
            System.out.println("Could not add member");
            return beerMain;
        }
    }

    @Override
    public BeerMain punishMember(BeerMain beerMain, String member, String description, int value) throws IOException {
        try{
            BeerMain beerMain2 = getBeermain();
            Rule rule = new Rule(description, value);
            beerMain2.punishMember(member, rule);
            writeBeerMainToJson(beerMain2);
            return beerMain2;
        }catch(IOException ioe){
            System.out.println("Could not punish member");
            return beerMain;
        }

    }

    @Override
    public void removeRule(BeerMain beerMain, String ruleDescription) throws IOException {
        beerMain = getBeermain();
        beerMain.removeRuleUsingDescription(ruleDescription);
        writeBeerMainToJson(beerMain);
    }

    @Override
    public void deleteMember(BeerMain beerMain, String member) throws IOException {
        beerMain = getBeermain();
        beerMain.deleteMember(member);
        writeBeerMainToJson(beerMain);
    }

    @Override
    public void payPunishment(BeerMain beerMain, String member, String description, int value) throws IOException {
        beerMain = getBeermain();
        Rule rule = new Rule(description, value);
        beerMain.removePunishment(member, rule);
        writeBeerMainToJson(beerMain);
    }
}