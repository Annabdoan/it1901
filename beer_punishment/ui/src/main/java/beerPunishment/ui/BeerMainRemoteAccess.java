package beerPunishment.ui;
import beerPunishment.core.BeerMain;
import beerPunishment.json.JsonHandler;
import com.google.gson.Gson;
import beerPunishment.core.Rule;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.URIParameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.net.http.HttpRequest.BodyPublishers;
import static org.springframework.http.MediaType.APPLICATION_JSON;


public class BeerMainRemoteAccess implements IBeerMainAccess {

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

    private JsonHandler jsh;

    public static Boolean pingServer(URI baseURI ) {

        HttpRequest request = HttpRequest.newBuilder( baseURI.resolve("ping"))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();
        try {
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Successfully pinged server");
            return response.body().equals("pong");
        } catch (IOException | InterruptedException e) {
            System.out.println("Could not find server");
            return false;
        }

    }



    public BeerMain getBeermain() {
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder(path.resolve("beerMain"))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();
        try {
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            //DEBUG
            System.out.println(response.body());
            BeerMain bm = gson.fromJson(response.body(), BeerMain.class);
            return bm;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private String UriParam(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private URI beerMainPath(String name) {
        return path.resolve(UriParam(name));
    }


    /**
     * Sends a POST-request ....
     */
    public BeerMain addRule(BeerMain beerMain, String description, int value) {
        try {
            HttpRequest request = HttpRequest.newBuilder(
                            beerMainPath("rules"))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .header(CONTENT_TYPE_HEADER, APPLICATION_FORM_URLENCODED)
                    .POST(HttpRequest.BodyPublishers.ofString("?description=" + description + "&value=" + value))
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
                beerMain2.addRule(new Rule(description, value));
                return beerMain2;
            } else {
                return beerMain;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public BeerMain addMember(BeerMain beerMain, String name) {
        try {
            HttpRequest request = HttpRequest.newBuilder(
                            beerMainPath("addMember"))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .header(CONTENT_TYPE_HEADER, APPLICATION_FORM_URLENCODED)
                    .POST(HttpRequest.BodyPublishers.ofString("?name=" + name))
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
                beerMain2.addMember(name);
                return beerMain2;
            } else {
                return beerMain;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends a PUT-request and updates the attribute of the user.
     *
     * @param member the member to punish,................
     */
    public BeerMain punishMember(BeerMain beerMain, String member, String description, int value) {
        try {
            HttpRequest request = HttpRequest.newBuilder(beerMainPath("punishMember"))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                    .PUT(BodyPublishers.ofString("?member=" + member +
                            "&description="+description + "&value=" + value))
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.body() != null) {
                Rule rule = new Rule(description, value);
                BeerMain beerMain2 = getBeermain();
                beerMain2.punishMember(member, rule);
                return beerMain2;
            } else {
                return beerMain;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Removes the TodoList with the given name from the underlying TodoModel. ENDRE DENNE!!!!!
     *
     * @param ruleDescription the rule to remove
     */
    public BeerMain removeRule(BeerMain beerMain, String ruleDescription) {
        try {
            HttpRequest request = HttpRequest.newBuilder(beerMainPath("removeRule"))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .DELETE()
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
                beerMain2.removeRuleUsingDescription(ruleDescription);
                return beerMain2;
            } else {
                return beerMain;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    public BeerMain deleteMember(BeerMain beerMain, String member) {

        try {
            HttpRequest request = HttpRequest.newBuilder(beerMainPath("deleteMember"))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .DELETE()
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
                beerMain2.deleteMember(member);
                return beerMain2;
            } else {
                return beerMain;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void payPunishment(String member, String description, int value) {
        Gson gson = new Gson();
        Rule rule = new Rule(description,value);
        try {
            HttpRequest request = HttpRequest.newBuilder(beerMainPath("payPunishment"))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .DELETE()
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();

            Boolean removed = gson.fromJson(responseString, Boolean.class);
            if (removed != null) {
                beerMain.removePunishment(member,rule);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
