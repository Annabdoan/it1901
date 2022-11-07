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
    void updateListView();

    void updateRuleChoicebox();

    void updatePersonChoicebox();

    /**
     * Show error message.
     *
     * @param errorMessage The message to show in the error
     */

    void showErrorMessage(String errorMessage);

    /**
     * Make new rule.
     */
    void makeNewRule();

    void deleteRule();

    void updateMemberView();

    /**
     * Punish a member.
     */
    void punishMember();

    /**
     * Add member.
     */
    void addMember();

    void deleteMember();

    void payViolation();

    void updatePaymentRuleChoicebox();

    void updatePaymentPersonChoicebox();

    BeerMain getBeermain();

    void setFileName(String fileName);
}
