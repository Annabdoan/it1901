package beerPunishment.ui;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import beerPunishment.json.JsonHandler;
import java.io.IOException;



/**
 * Class that centralizes access to a BeerMain object.
 * Makes it easier to support transparent use of a REST API.
 */
public class BeerMainLocalAccess implements IBeerMainAccess {

    private BeerMain beerMain;
    private JsonHandler jsh;
    private Rule rule;
    private String filename = "/beerPunishment.json";

    public BeerMainLocalAccess() {
        this.jsh = new JsonHandler();

    }

    /**
     * Sets the name of the local json file.
     *
     * @param newName the name to set the local json file
     */
    public void changeLocalFilename(String newName) {
        this.filename = newName;
    }

    private void writeBeerMainToJson(BeerMain beerMain) throws IOException {
        try {
            jsh.writeToJson(beerMain, filename);

        } catch (IOException ioe) {
            throw new IOException("Feil ved skriving til fil.");
        }
    }

    @Override
    public BeerMain getBeermain() {
        jsh = new JsonHandler();
        try {
            beerMain = jsh.readFromJson(filename);
            if (beerMain == null) {
                beerMain = new BeerMain();
            }
            BeerMain beerMainCopy = new BeerMain();
            beerMainCopy.copy(beerMain);
            return beerMainCopy;
        } catch (IOException ioe) {
            return new BeerMain();
        }
    }

    @Override
    public BeerMain addRule(BeerMain beerMain, String description, int value) {
        try {
            rule = new Rule(description, value);
            BeerMain beerMain2 = getBeermain();
            beerMain2.addRule(rule);
            writeBeerMainToJson(beerMain2);
            return beerMain2;
        } catch (IOException ioe) {
            System.out.println("Could not add rule");
            return beerMain;
        }
    }

    @Override
    public BeerMain deleteRule(BeerMain beerMain, String ruleDescription) {
        try {
            BeerMain beerMain2 = getBeermain();
            beerMain2.deleteRuleUsingDescription(ruleDescription);
            writeBeerMainToJson(beerMain2);
            return beerMain2;
        } catch (IOException ioe) {
            System.out.println("Could not remove rule");
            return beerMain;
        }
    }

    @Override
    public BeerMain addMember(BeerMain beerMain, String name) {
        try {
            BeerMain beerMain2 = getBeermain();
            beerMain2.addMember(name);
            writeBeerMainToJson(beerMain2);
            return beerMain2;
        } catch (IOException ioe) {
            System.out.println("Could not add member");
            return beerMain;
        }
    }

    @Override
    public BeerMain deleteMember(BeerMain beerMain, String member) {
        try {
            BeerMain beerMain2 = getBeermain();
            beerMain2.deleteMember(member);
            writeBeerMainToJson(beerMain2);
            return beerMain2;
        } catch (IOException ioe) {
            System.out.println("Could not delete member");
            return beerMain;
        }
    }

    @Override
    public BeerMain punishMember(BeerMain beerMain, String member, String description, int value) {
        try {
            BeerMain beerMain2 = getBeermain();
            Rule rule = new Rule(description, value);
            beerMain2.punishMember(member, rule);
            writeBeerMainToJson(beerMain2);
            return beerMain2;
        } catch (IOException ioe) {
            System.out.println("Could not punish member");
            return beerMain;
        }
    }

    @Override
    public BeerMain deletePunishment(BeerMain beerMain, String member, String description, int value) {
        try {
            BeerMain beerMain2 = getBeermain();
            Rule rule = new Rule(description, value);
            beerMain2.deletePunishment(member, rule);
            writeBeerMainToJson(beerMain2);
            return beerMain2;
        } catch (IOException ioe) {
            System.out.println("Could not delete punishment");
            return beerMain;
        }
    }
}