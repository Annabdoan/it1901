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
    void addRule(String description, int value);


    /**
     * Add member.
     */
    void addMember(String name);

    /**
     * Punish a member.
     */
    void punishMember(String member, String description, int value);

    void removeRule(String ruleDescription);


    void deleteMember(String member);

    void payPunishment(String member, String description, int value);


}
