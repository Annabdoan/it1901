package beerPunishment.core;





import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleTest {

    private Rule rule;
    private Rule rule2;

    @BeforeEach
    public void setup() {
        rule = new Rule("Ikke dukket opp", 2);
    }

    @Test
    public void testRuleConstructor() {
        assertEquals(rule.getDescription(), "Ikke dukket opp");
        assertEquals(rule.getPunishmentValue(), 2);
    }

    @Test
    public void testToString() {
        String expected = "[Rule rule=Ikke dukket opp value=2]";
        assertEquals(expected, rule.toString());
    }










}
