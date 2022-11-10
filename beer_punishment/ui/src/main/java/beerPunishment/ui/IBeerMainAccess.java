package beerPunishment.ui;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import beerPunishment.json.JsonHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public interface IBeerMainAccess {

    BeerMain getBeermain();

    /**
     * Make new rule.
     */
    BeerMain addRule(String description, int value) throws IOException;


    /**
     * Add member.
     */
    BeerMain addMember(String name) throws IOException;

    /**
     * Punish a member.
     */
    BeerMain punishMember(String member, String description, int value) throws IOException; // ende voids

    BeerMain removeRule(String ruleDescription) throws IOException;


    BeerMain deleteMember(String member) throws IOException;

    BeerMain payPunishment(String member, String description, int value) throws IOException;


}
