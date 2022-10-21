package beerPunishment.json;

import beerPunishment.core.BeerMain;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public void writeToJson(BeerMain beerMain, String filename) throws IOException {
        try {
            FileWriter fw = new FileWriter(getFilePath(filename));
            gson.toJson(beerMain, fw);
            fw.flush();
            fw.close();
        }catch (IOException io) {
            throw new IOException("Feil ved skriving til fil");
        }
    }

    public BeerMain readFromJson(String filename) throws IOException {
        try {
            BeerMain beerMain = gson.fromJson(new FileReader(getFilePath(filename)), BeerMain.class);
            return beerMain;
        }catch (IOException IOe) {
            throw new IOException("Feil ved lesing fra fil");
        }
    }
}

