package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.json.JsonHandler;
import java.io.IOException;

/**
 * Configures the beerMain service.
 */
public class BeerMainService {

    /**
     * Initializes a BeerMain object in RestController.
     */
    public static BeerMain createBeerMain() {
        JsonHandler jsh = new JsonHandler();
        try {
            return jsh.readFromJson("/beerPunishmentRemote.json");
        } catch (IOException e) {
            System.out.println("Couldn't read beerPunishmentRemote.json, so rigging BeerMain manually ("
                    + e + ")");
        }
        BeerMain bm = new BeerMain();
        try {
            jsh.writeToJson(bm, "/beerPunishmentRemote.json");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return new BeerMain();
    }
}
