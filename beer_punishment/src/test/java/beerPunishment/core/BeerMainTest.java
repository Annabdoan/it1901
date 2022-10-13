package beerPunishment.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class BeerMainTest {

    private BeerMain beermain;
    private Rule rule;

   @BeforeEach
   public void setUp() {
       beermain = new BeerMain();
       rule = new Rule("Kommer sent", 1);
       beermain.addRule(rule);
   }

    @Test
    public void testListofRules() {
        assertEquals(1, rule.getPunishmentValue());
        assertEquals("Kommer sent", rule.getDescription());
    }

    @Test
    public void testManipulateListOfRules() {
       List<Rule> expectedRules = new ArrayList<>(List.of(rule));
       assertEquals(expectedRules, beermain.getRules(), "getRules should include the same rule as expectedRules.");
       List<Rule> ruleTestList = beermain.getRules();
       ruleTestList.remove(0);
       assertEquals(expectedRules, beermain.getRules(), "Should not be able to manipulate list of rules.");
    }



}
