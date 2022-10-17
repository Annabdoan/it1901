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

    public Persistence() {

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
            throw new IOException("Error in creating file");
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

}
