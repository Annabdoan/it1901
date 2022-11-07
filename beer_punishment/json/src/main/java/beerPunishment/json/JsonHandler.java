package beerPunishment.json;

import beerPunishment.core.BeerMain;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

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
        OutputStreamWriter fw = null; //to ensure reliance on default encoding
        try {
            fw = new OutputStreamWriter(new FileOutputStream(getFilePath(filename)), "UTF-8");
            gson.toJson(beerMain, fw);
        } catch (IOException io) {
            throw new IOException("Feil ved skriving til fil");
        }
        finally { //to secure against potential leak
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


    public BeerMain readFromJson(String filename) throws IOException {
        InputStreamReader isr = null;
        try {
            FileInputStream fis = new FileInputStream(getFilePath(filename));
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8); //reliance on default encoding
            BeerMain beerMain = gson.fromJson(isr, BeerMain.class);
            return beerMain;
        } catch (IOException IOe) {
            IOe.printStackTrace();
            throw new IOException("Feil ved lesing fra fil");
        } finally { //to secure against potential leak
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

