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
    void addRule();


    /**
     * Add member.
     */
    void addMember();

    /**
     * Punish a member.
     */
    void punishMember();

    void removeRule();


    void deleteMember();

    void payPunishment();


}
