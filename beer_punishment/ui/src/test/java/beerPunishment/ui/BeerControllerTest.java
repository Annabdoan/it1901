package beerPunishment.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BeerControllerTest extends ApplicationTest {

    private BeerController controller;


    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Beer.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void testAddRule() {
        File file = new File(System.getProperty("user.home"),"Rulelist.json");
        clickOn("#newRuleTextInput").write("Komme for sent;5");
        clickOn("#newRuleButton");
        Collection<Rule> expectedList = new ArrayList<>(List.of(new Rule("Komme for sent", 5)));
        Collection<Rule> actualList = this.controller.getBeermain().getRules();
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    public void testAddMember() {
        String newMemberText = "Maurice";
        clickOn("#addMemberText").write(newMemberText);
        clickOn("#addMemberButton");
        Collection<String> expectedMembers =new ArrayList<>(List.of("Maurice"));
        Collection<String> actualMemberList = this.controller.getBeermain().getUsernames();
        assertEquals(expectedMembers, actualMemberList);
    }

    @Test
    public void testPunishMember() {
        clickOn("#ruleChoiceBox");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#personChoiceBox");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#punishButton");
        BeerMain beerMain = this.controller.getBeermain();
        HashMap<String, Collection<Rule>> actualHashMap = beerMain.getMemberRuleViolations();
        Collection<Rule> expectedList = new ArrayList<>(List.of(new Rule("Komme for sent", 5)));
        assertEquals(expectedList.toString(), actualHashMap.get("Maurice").toString());
    }
    @Test
    public void testDeleteMember() {
        testAddMember();
        //testDeleteMember kjører før addMember (uavhengig av hvor i koden den ligger) derfor må et member
        // legges til først
        String deleteMemberText = "Maurice";
        clickOn("#deleteMemberText").write(deleteMemberText);
        clickOn("#deleteMemberButton");
        Collection<String> expectedMembers =new ArrayList<>(List.of());
        Collection<String> actualMemberList = this.controller.getBeermain().getUsernames();
        assertEquals(expectedMembers, actualMemberList);
    }
    @Test
    public void testDeleteRule() {
        File file = new File(System.getProperty("user.home"),"Rulelist.json");
        clickOn("#deleteRuleText").write("Komme for sent");
        clickOn("#deleteRuleButton");
        Collection<Rule> expectedList = new ArrayList<>(List.of());
        Collection<Rule> actualList = this.controller.getBeermain().getRules();
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @AfterAll
    public static void reset() {
        File file = new File(System.getProperty("user.home"),"/beerPunishment.json");
        file.delete();
    }
}
