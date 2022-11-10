package beerPunishment.ui;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import beerPunishment.json.JsonHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class BeerMainLocalAccess implements IBeerMainAccess {

    private BeerMain beerMain;
    private JsonHandler jsh;
    private Rule rule;

    private void writeBeerMainToJson(BeerMain beerMain){
        try {
            jsh.writeToJson(beerMain, "/beerPunishment.json");

        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    @Override
    public BeerMain getBeermain() {
        try {
            beerMain = jsh.readFromJson("/beerPunishment.json");
            return beerMain;
        }catch (IOException ioe){
            return new BeerMain();
        }
    }

    @Override
    public void addRule(String description, int value) {
        rule = new Rule(description, value);
        beerMain = getBeermain();
        beerMain.addRule(rule);
        writeBeerMainToJson(beerMain);
    }

    @Override
    public void addMember(String name) {
        beerMain = getBeermain();
        beerMain.addMember(name);
        writeBeerMainToJson(beerMain);
    }

    @Override
    public void punishMember(String member, String description, int value) {
        beerMain = getBeermain();
        Rule rule = new Rule(description, value);
        beerMain.addRule(rule);
        beerMain.punishMember(member, rule);
        writeBeerMainToJson(beerMain);
    }

    @Override
    public void removeRule(String ruleDescription) {

    }

    @Override
    public void deleteMember(String member) {

    }

    @Override
    public void payPunishment(String member, String description, int value) {

    }
}