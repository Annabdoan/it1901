package beerPunishment.json;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class JsonHandlerTest {

    private JsonHandler jsonHandler = new JsonHandler();
    private BeerMain beerMain;
    Rule rule1;

    @BeforeEach
    public void setup() {
        beerMain = new BeerMain();
        Rule rule1 = new Rule("Komme for seint", 2);
        Rule rule2 = new Rule("Være på " +
                "mobilen", 1);
        beerMain.addRule(rule1);
        beerMain.addRule(rule2);
        beerMain.addMember("Lea");
        jsonHandler.getFilePath("/Test.json");
    }


    @Test
    public void testWriteToJson() {
        assertDoesNotThrow(() -> jsonHandler.writeToJson(beerMain,"/Test.json" ));
        assertThrows(IOException.class, () -> {
            jsonHandler.writeToJson(beerMain, null);
        });
    }


    @Test
    public void testReadFromJson() {
        assertDoesNotThrow(() -> jsonHandler.readFromJson("/Test.json"));
        assertThrows(IOException.class, () -> {
            jsonHandler.readFromJson(null);
        });
    }


    @Test
    public void testEditJsonFile() {
        try {
            jsonHandler.readFromJson("/Test.json");
            assertTrue(beerMain.getUsernames().contains("Lea"));
            beerMain.punishMember("Lea", rule1);
            jsonHandler.writeToJson(beerMain, "/Test.json");
            BeerMain beermain1 = jsonHandler.readFromJson("/Test.json");
            assertTrue(beermain1.getMemberRuleViolations().containsKey("Lea"));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @AfterEach
    public void reset() {
        new File("Test.json").delete();
    }


}
