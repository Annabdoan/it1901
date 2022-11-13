package beerPunishment.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.testfx.framework.junit5.ApplicationTest;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.testfx.matcher.control.LabeledMatchers;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeerControllerTest extends ApplicationTest {

    private BeerController controller;


    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Beer.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
        this.controller.changeFileName("/beerPunishmentTest.json");
    }
    @Order(1)
    @Test
    public void testAddRule() {
        clickOn("#newRuleTextInput").write("Komme for sent;5");
        clickOn("#newRuleButton");
        Collection<Rule> expectedList = new ArrayList<>(List.of(new Rule("Komme for sent", 5)));
        Collection<Rule> actualList = this.controller.getBeermain().getRules();
        assertEquals(expectedList.toString(), actualList.toString());
        clickOn("#newRuleTextInput").eraseText(16);
        clickOn("#newRuleTextInput").write("Komme for sent;-5");
        clickOn("#newRuleButton");
        clickOn(LabeledMatchers.hasText("OK"));

    }
    @Order(2)
    @Test
    public void testAddMember() {
        String newMemberText = "Maurice";
        clickOn("#addMemberText").write(newMemberText);
        clickOn("#addMemberButton");
        Collection<String> expectedMembers =new ArrayList<>(List.of("Maurice"));
        Collection<String> actualMemberList = this.controller.getBeermain().getUsernames();
        assertEquals(expectedMembers, actualMemberList);
    }
    @Order(3)
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
    @Order(4)
    @Test
    public void testPayViolation() {
        clickOn("#paymentMemberChoiceBox");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#paymentRuleChoiceBox");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#payButton");
        BeerMain beerMain = this.controller.getBeermain();
        HashMap<String, Integer> actualHashMapInt = beerMain.generateMembersPunishments();
        String expected = "0";
        assertEquals(expected, actualHashMapInt.get("Maurice").toString());
        HashMap<String, Collection<Rule>> actualHashMapRule = beerMain.getMemberRuleViolations();
        Collection<Rule> expectedList = new ArrayList<>(List.of());
        assertEquals(expectedList.toString(), actualHashMapRule.get("Maurice").toString());
    }
    @Order(5)
    @Test
    public void testDeleteMember() {
        String deleteMemberText = "Maurice";
        clickOn("#deleteMemberText").write(deleteMemberText);
        clickOn("#deleteMemberButton");
        Collection<String> expectedMembers =new ArrayList<>(List.of());
        Collection<String> actualMemberList = this.controller.getBeermain().getUsernames();
        assertEquals(expectedMembers, actualMemberList);
        clickOn("#deleteMemberText").eraseText(7);
        String deleteFalseMemberText = "Sara";
        clickOn("#deleteMemberText").write(deleteFalseMemberText);
        clickOn("#deleteMemberButton");
        clickOn(LabeledMatchers.hasText("OK"));
    }
    @Order(6)
    @Test
    public void testDeleteRule() {
        File file = new File(System.getProperty("user.home"),"Rulelist.json");
        clickOn("#deleteRuleText").write("Komme for sent");
        clickOn("#deleteRuleButton");
        Collection<Rule> expectedList = new ArrayList<>(List.of());
        Collection<Rule> actualList = this.controller.getBeermain().getRules();
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    public void testShowErrorMessage() {

    }
    @AfterAll
    public static void reset() {
        File file = new File(System.getProperty("user.home"),"/beerPunishmentTest.json");
        file.delete();
    }
}
