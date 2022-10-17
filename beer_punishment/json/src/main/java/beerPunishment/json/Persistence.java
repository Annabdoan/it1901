package beerPunishment.json;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

/**
 * Class for persistence using jackson serializer and deserializer.
 */
public class Persistence {
    /**
     * Used to indicate what parts of a RuleModel to serialize.
     */

    private ObjectMapper mapper = new ObjectMapper().registerModule(createModule());
    private File ruleFile;
    private BeerMain beerMain;

    public Persistence() {
        ruleFile = newFile("ruleSaveFile.json");
    }

    public File newFile(String filename) {
        return new File(filename);
    }

    public RuleModule createModule() {
        return new RuleModule();
    }

    /**
     * Creates a file.
     *
     * @param fileName the filename of the json-file
     */
    public void createFile(String fileName) throws IOException {
        this.ruleFile = newFile(fileName);
        if (newFile(fileName).createNewFile()) {
            //if true or false, file exist or not
        } else {
            beerMain.addRule(readRule(ruleFile));
        }
    }

    /**
     * Reads rules from file.
     *
     * @param file the filename of the json-file to read from
     */
    public Rule readRule(File file) throws IOException {
        return mapper.readValue(file, Rule.class);
    }

    /**
     * Loads beermain-object from the saved file.
     *
     * @return the loaded BeerMain objects
     */
    public BeerMain readBeerMain(File file) throws IOException {
        return mapper.readValue(file, BeerMain.class);
    }

    /**
     * Write beermain-object to the saved file.
     */
    public void writeBeerMain(BeerMain beerMain, File file) throws IOException {
        mapper.writeValue(file, beerMain);
    }
    /**
     * Main method.
     */
    public static void main(String[] args) throws IOException {
        Persistence rulepersistence = new Persistence();
        String fileName = "ruleTest.json";
        rulepersistence.createFile(fileName);
        Rule rule1 = new Rule();
        Rule rule2 = new Rule();
        rule1.setDescription("Komme for sent");
        rule1.setPunishmentValue(5);
        rule2.setDescription("Maurice bannet");
        rule2.setPunishmentValue(8);
        BeerMain ruleList = new BeerMain();
        ruleList.addRule(rule1);
        ruleList.addRule(rule2);
        ruleList.addMember("Maurice");
        ruleList.addMember("Anna");
        ruleList.punishMember("Anna", rule2);
        ruleList.punishMember("Anna", rule2);
        ruleList.punishMember("Maurice", rule1);
        System.out.println(ruleList);
        //rulepersistence.writeRule(ruleList, fileName);
        //BeerMain beerMain3 = rulepersistence.readBeerMain(rulepersistence.ruleFile);
        //beerMain3.addMember("Lea");
        //System.out.println(beerMain3);
    }
}
