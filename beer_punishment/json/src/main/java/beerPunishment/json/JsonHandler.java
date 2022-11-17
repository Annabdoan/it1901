package beerPunishment.json;

import beerPunishment.core.BeerMain;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * A class for writing and reading from json file.
 */
public class JsonHandler {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();


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
        OutputStreamWriter fw = null;
        try {
            fw = new OutputStreamWriter(new FileOutputStream(getFilePath(filename)), "UTF-8");
            gson.toJson(beerMain, fw);
        } catch (IOException io) {
            throw new IOException("Feil ved skriving til fil");
        } finally {
            if (fw != null) {
                try {
                    fw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads from JSON file and makes it a BeerMain object.
     *
     * @param filename the filename to read from
     */
    public BeerMain readFromJson(String filename) throws IOException {
        InputStreamReader isr = null;
        try {
            FileInputStream fis = new FileInputStream(getFilePath(filename));
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8); //reliance on default encoding
            BeerMain beerMain = gson.fromJson(isr, BeerMain.class);
            return beerMain;
        } catch (IOException ioe) {
            throw new IOException("Feil ved lesing fra fil");
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

