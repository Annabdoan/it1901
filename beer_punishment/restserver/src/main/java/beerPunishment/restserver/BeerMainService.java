package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.json.JsonHandler;

import java.io.IOException;
import java.net.URL;

public class BeerMainService {

    private BeerMain beerMain;

    public static BeerMain createBeerMain() {
        JsonHandler jsh = new JsonHandler();
        try {
            return jsh.readFromJson("/beerPunishment.json");
        } catch (IOException e) {
            System.out.println("Couldn't read beerPunishment.json, so rigging BeerMain manually ("
                    + e + ")");
        }
        return new BeerMain();
    }


}
