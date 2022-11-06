package beerPunishment.json;

import beerPunishment.core.BeerMain;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class for writing and reading from json file.
 */
public class JsonHandler {

    Gson gson = new Gson();

    /**
     * Sets the filepath to and set name to the jsonfile.
     *
     * @param filename the filename to set
     */
    public String getFilePath(String filename) {
        return System.getProperty("user.home") + filename;
    }

    /**
     * Writes to JSON.
     *
     * @param beerMain the object being written to file
     * @param filename name of the file
     */
    public void writeToJson(BeerMain beerMain, String filename) throws IOException {
        try {
            FileWriter fw = new FileWriter(getFilePath(filename));
            gson.toJson(beerMain, fw);
            fw.flush();
            fw.close();
        } catch (IOException io) {
            throw new IOException("Feil ved skriving til fil");
        }
    }

    /**
     * Reads from JSON file and makes it a BeerMain object.
     *
     * @param filename the filename to read from
     */
    public BeerMain readFromJson(String filename) throws IOException {
        try {
            BeerMain beerMain = gson.fromJson(new FileReader(getFilePath(filename)), BeerMain.class);
            return beerMain;
        } catch (IOException ioe) {
            throw new IOException("Feil ved lesing fra fil");
        }
    }
}

