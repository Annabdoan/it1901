package beerPunishment.json;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.FileSystemLoopException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class RulePersistence {
    /**
     * Used to indicate what parts of a RuleModel to serialize.
     */

    private ObjectMapper mapper = new ObjectMapper().registerModule(createModule());
    private Path filePath;
    private String fileName;
    private File ruleFile;
    private BeerMain beerMain = new BeerMain();

    public RulePersistence() {
        ruleFile = newFile("ruleFile.json");
    }

    public void mockPrint() {
        System.out.println("wahoo");
    }

    public RuleModule createModule() {
        return new RuleModule();
    }
    public void createFile(String fileName) throws IOException {
        this.ruleFile = newFile(fileName);
        if (newFile(fileName).createNewFile()) {
            //if true or false, file exist or not
        } else {
            beerMain.addRule(readRule(ruleFile));
        }
    }

    public File newFile(String filename) {
        return new File(filename);
    }


    public Rule readRule(File reader) throws IOException {
        return mapper.readValue(reader, Rule.class);
    }

    public BeerMain readBeerMain(File reader) throws IOException {
        return mapper.readValue(reader, BeerMain.class);
    }


    public void writeRule(BeerMain ruleList, String filename) throws IOException {
        mapper.writeValue(ruleFile, ruleList);
    }

    public static void main(String[] args) throws IOException {
        RulePersistence rulepersistence = new RulePersistence();
        String fileName = "ruleTest.json";
        rulepersistence.createFile(fileName);
        BeerMain ruleList = new BeerMain();
        Rule rule1 = new Rule();
        Rule rule2 = new Rule();
        rule1.setDescription("Komme for sent");
        rule1.setPunishmentValue(5);
        rule2.setDescription("Maurice bannet");
        rule2.setPunishmentValue(8);
        ruleList.addRule(rule1);
        ruleList.addRule(rule2);
        ruleList.addMember("Maurice");
        ruleList.addMember("Anna");
        ruleList.punishMember("Anna", rule2);
        ruleList.punishMember("Anna", rule2);
        ruleList.punishMember("Maurice", rule1);
        System.out.println(ruleList);
        rulepersistence.writeRule(ruleList, fileName);
        BeerMain beerMain3 = rulepersistence.readBeerMain(rulepersistence.ruleFile);
        beerMain3.addMember("Lea");
        System.out.println(beerMain3);
    }
}
