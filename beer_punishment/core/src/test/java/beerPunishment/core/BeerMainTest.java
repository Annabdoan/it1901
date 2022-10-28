package beerPunishment.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class BeerMainTest {

    private BeerMain beermain;
    private Rule rule;

   @BeforeEach
   public void setUp() {
       beermain = new BeerMain();
       rule = new Rule("Kommer sent", 1);
       beermain.addRule(rule);
       beermain.addMember("Test");
   }

    @Test
    public void testListofRules() {
        assertEquals(1, rule.getPunishmentValue());
        assertEquals("Kommer sent", rule.getDescription());
        Rule tempRule = new Rule("Være på mobil", 2);
        Collection<Rule> expectedRules = new ArrayList<>(List.of(rule, tempRule));
        beermain.addRule(tempRule);
        assertEquals(expectedRules, beermain.getRules(), "Rulelist should now consist of rule and tempRule");
        beermain.removeRule(tempRule);
        expectedRules.remove(tempRule);
        assertEquals(expectedRules, beermain.getRules(), "Rulelist should now consist of only rule and not tempRule");
    }

    @Test
    public void testManipulateListOfRules() {
       Collection<Rule> expectedRules = new ArrayList<>(List.of(rule));
       assertEquals(expectedRules, beermain.getRules(), "getRules should include the same rule as expectedRules.");
       Collection<Rule> ruleTestList = beermain.getRules();
       ruleTestList.remove(0);
       assertEquals(expectedRules, beermain.getRules(), "Should not be able to manipulate list of rules.");
    }

    @Test
    public void testMembersHashMap() {
        HashMap<String, Collection<Rule>> expectedMap = new HashMap<>();
        expectedMap.put("Test", new ArrayList<>());
        assertEquals(expectedMap, beermain.getMemberRuleViolations(), "The two maps should be equal");
        beermain.addMember("Test2");
        expectedMap.put("Test2", new ArrayList<>());
        assertEquals(expectedMap, beermain.getMemberRuleViolations(), "beermain should now include the username Test2 in its map");
        beermain.punishMember("Test", rule);
        expectedMap.put("Test", new ArrayList<>(List.of(rule)));
        assertEquals(expectedMap, beermain.getMemberRuleViolations(), "Test should now be punished for breaking rule in both maps");
        Collection<String> expectedPunishmentValues = new ArrayList<>(List.of("Test\t\t\t\t\t1", "Test2\t\t\t\t\t0"));
        assertEquals(expectedPunishmentValues, beermain.generatePunishmentStatusToString(), "The toString showcasing each members punishmentstatus should be as in expectedPunishmentValues");
        Collection<String> usernames = new ArrayList<>(List.of("Test", "Test2"));
        assertEquals(usernames, beermain.getUsernames(), "The usernames should be Test and Test2");
        String expectedToString = "BeerMain{rules=[[Rule rule=Kommer sent value=1]], memberRuleViolations={Test=[[Rule rule=Kommer sent value=1]], Test2=[]}}";
        assertEquals(expectedToString, beermain.toString(), "toString should match expected toString");
    }

    @Test
    public void testManipulateMemberRuleViolations() {
        HashMap<String, Collection<Rule>> expectedMap = new HashMap<>();
        expectedMap.put("Test", new ArrayList<>());
        HashMap<String, Collection<Rule>> testMap = beermain.getMemberRuleViolations();
        testMap.clear();
        assertNotEquals(testMap, beermain.getMemberRuleViolations(), "the cleared map should be different to the beerMain objects map");
        assertEquals(expectedMap, beermain.getMemberRuleViolations(), "Should not be able to manipulate list of rules.");
    }



}
