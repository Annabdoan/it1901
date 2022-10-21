package beerPunishment.json;

import beerPunishment.core.BeerMain;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonHandler {

    Gson gson = new Gson();

    public String getFilePath(String filename) {
        return System.getProperty("user.home") + filename;
    }

    public void writeToJson(BeerMain beerMain, String filename) throws IOException {
        FileWriter fw = new FileWriter(getFilePath(filename));
        gson.toJson(beerMain, fw);
        fw.flush();
        fw.close();
    }

    public BeerMain readFromJson(String filename) throws FileNotFoundException {
        BeerMain beerMain = gson.fromJson(new FileReader(getFilePath(filename)), BeerMain.class);
        return beerMain;
    }


}

