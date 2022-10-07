package beerPunishment.core;


import static org.junit.jupiter.api.Assertions.assertEquals;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RuleTest {

    private Rule rule;

    @BeforeEach
    public void setup() {
        rule = new Rule("Ikke møtt opp", 2);
    }

    @Test
    public void testRuleConstructor() {

    }




}
