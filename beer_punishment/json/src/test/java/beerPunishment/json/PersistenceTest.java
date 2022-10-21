package beerPunishment.json;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class PersistenceTest {
/*
    private Persistence persistence = new Persistence();
    private BeerMain beerMain;

    @BeforeEach
    private void setup() {
        beerMain = new BeerMain();
        Rule rule1 = new Rule("Komme for seint", 2);
        Rule rule2 = new Rule("Være på mobilen", 1);
        beerMain.addRule(rule1);
        beerMain.addRule(rule2);
        try{
            persistence.createFile("Test.json");
        }
        catch (IOException io) {
            System.out.println("Could not create file");
        }
    }

    @Test
    public void testReadWriteFile() {
        try {
            persistence.writeBeerMain(beerMain, new File("Test.json"));
        }catch (IOException testIOe) {
            fail(testIOe.getMessage());
        }
    }

    @AfterEach
    public void reset() {
        new File("Test.json").delete();
    } */
}