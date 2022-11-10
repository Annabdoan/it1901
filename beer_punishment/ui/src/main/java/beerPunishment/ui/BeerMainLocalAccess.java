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


    @Override
    public BeerMain getBeermain() {
        try {
            this.beerMain = jsh.readFromJson("/beerPunishment.json");
            return this.beerMain;
        }catch (IOException ioe){
            return new BeerMain();
        }
    }

    @Override
    public void addRule(String description, int value) {

    }

    @Override
    public void addMember(String name) {

    }

    @Override
    public void punishMember(String member, String description, int value) {

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