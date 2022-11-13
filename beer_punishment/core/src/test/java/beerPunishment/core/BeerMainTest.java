package beerPunishment.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
        beermain.deleteRule(tempRule);
        expectedRules.remove(tempRule);
        assertEquals(expectedRules, beermain.getRules(), "Rulelist should now consist of only rule and not tempRule");
        Exception exception = assertThrows(Exception.class, () -> beermain.deleteRule(tempRule));
        assertEquals("Regelen eksisterer ikke", exception.getMessage());
        Exception exception2 = assertThrows(Exception.class, () -> beermain.addRule(rule));
        assertEquals("Ikke lov å lage samme regel to ganger", exception2.getMessage());
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
        beermain.deleteMember("Test");
        Collection<String> usernames2 = new ArrayList<>(List.of("Test2"));
        assertEquals(usernames2, beermain.getUsernames(), "The usernames should be Test2");
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

    @Test
    public void testRemovePunishment() {
        HashMap<String, Collection<Rule>> expectedMap = new HashMap<>();
        expectedMap.put("Test", new ArrayList<>());
        beermain.addMember("Lea");
        beermain.punishMember("Lea", rule);
        beermain.removePunishment("Lea", rule);
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(expected, beermain.getMemberViolations("Lea"));
        Exception exception = assertThrows(Exception.class, () -> beermain.removePunishment("Lea", rule));
        assertEquals("Du har ikke brutt denne regelen", exception.getMessage());
        Exception exception2 = assertThrows(Exception.class, () -> beermain.removePunishment("Sara", rule));
        assertEquals("Brukernavnet eksisterer ikke", exception2.getMessage());
    }

    @Test
    public void testRemoveRuleUsingDescription() {
       beermain.removeRuleUsingDescription("Kommer sent");
       Collection<Rule> expectedRules = new ArrayList<>(List.of());
       assertEquals(expectedRules, beermain.getRules(), "Rulelist should now be empty");
       Exception exception = assertThrows(Exception.class, () -> beermain.removeRuleUsingDescription("Banne"));
       assertEquals("Regel eksisterer ikke", exception.getMessage());
    }


}
