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
    void addRule(String description, int value) throws IOException;


    /**
     * Add member.
     */
    void addMember(String name) throws IOException;

    /**
     * Punish a member.
     */
    void punishMember(String member, String description, int value) throws IOException;

    void removeRule(String ruleDescription) throws IOException;


    void deleteMember(String member) throws IOException;

    void payPunishment(String member, String description, int value) throws IOException;


}
