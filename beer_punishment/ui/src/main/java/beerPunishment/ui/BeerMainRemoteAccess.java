package beerPunishment.ui;
import beerPunishment.core.BeerMain;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import static org.springframework.http.MediaType.APPLICATION_JSON;


public class BeerMainRemoteAccess {

    private final URI path;

    private static final String APPLICATION_JSON = "application/json";

    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

    private static final String ACCEPT_HEADER = "Accept";

    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    //denne må være localhost:8080/
    public BeerMainRemoteAccess(URI path){
        this.path = path;
    }

    private BeerMain beerMain = new BeerMain();



    public BeerMain getBeerMain() {
        Gson gson = new Gson();
        if (beerMain == null) {
            HttpRequest request = HttpRequest.newBuilder(path.resolve("beerMain"))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .GET()
                    .build();
            try {
                final HttpResponse<String> response =
                        HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
                this.beerMain = gson.fromJson(response.body(), BeerMain.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        return beerMain;
    }




}
