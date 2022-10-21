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
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BeerControllerTest extends ApplicationTest {
/*
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
        List<Rule> expectedList = new ArrayList<>(List.of(new Rule("Komme for sent", 5)));
        List<Rule> actualList = this.controller.getBeermain().getRules();
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    public void testAddMember() {
        String newMemberText = "Maurice";
        clickOn("#addMemberText").write(newMemberText);
        clickOn("#addMemberButton");
        String expectedMembers = "Maurice";
        List<String> actualMemberList = this.controller.getBeermain().getUsernames();
        assertEquals(expectedMembers, actualMemberList.get(0));
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
        HashMap<String, List<Rule>> actualHashMap = beerMain.getMemberRuleViolations();
        List<Rule> expectedList = new ArrayList<>(List.of(new Rule("Komme for sent", 5)));
        assertEquals(expectedList.toString(), actualHashMap.get("\"Maurice\"").toString());
    }


    @AfterAll
    public static void reset() {
        File file = new File(System.getProperty("user.home"),"beersystem.json");
        file.delete();
    }
*/
}
