package beerPunishment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
