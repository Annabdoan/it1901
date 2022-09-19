package beerPunishment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
   }

    @Test
    public void testListofRules() {
        beermain.addRule(rule);
        assertEquals(1, rule.getPunishmentValue());
        assertEquals("Kommer sent", rule.getDescription());
    }

}
