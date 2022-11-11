package beerPunishment.ui;
import beerPunishment.core.BeerMain;
import beerPunishment.json.JsonHandler;
import com.google.gson.Gson;
import beerPunishment.core.Rule;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpRequest.BodyPublishers;








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

    private static final URI defaultURI = URI.create("http://localhost:8080");


    private String replaceSpace(String input){
        String fixedInput = input.replaceAll("\\s","%20");
        return fixedInput;
    }

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
        HttpRequest request = HttpRequest.newBuilder(defaultURI.resolve("/beerMain"))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();
        try {
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            //DEBUG
            final String responsebody = response.body();
            System.out.println(response.body());
            BeerMain bm = gson.fromJson(responsebody, BeerMain.class);
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
            HttpRequest request = HttpRequest.newBuilder(defaultURI.resolve("/rules"))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .header(CONTENT_TYPE_HEADER, APPLICATION_FORM_URLENCODED)
                    .POST(HttpRequest.BodyPublishers.ofString("description=" + description + "&value=" + value))
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
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
            HttpRequest request = HttpRequest.newBuilder(defaultURI.resolve("/members"))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .header(CONTENT_TYPE_HEADER, APPLICATION_FORM_URLENCODED)
                    .POST(HttpRequest.BodyPublishers.ofString("name=" + name))
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
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
        String putMappingPath = "/punishMember?";
        String key1 = "member=";
        String descriptionPath = "&description=";
        String valuePath = "&value=";
        String memberValue = String.valueOf(value);
        String fixedDescription = replaceSpace(description);
        System.out.println(fixedDescription);
        Gson gson = new Gson();
        try {
            String json = gson.toJson(beerMain, BeerMain.class);
            HttpRequest request = HttpRequest.newBuilder(defaultURI.resolve(
                            putMappingPath + key1 + member
                                    + descriptionPath + fixedDescription + valuePath + memberValue
                    ))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
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
        String fixedRuleDescription = replaceSpace(ruleDescription);
        try {
            HttpRequest request = HttpRequest.newBuilder(defaultURI.resolve("/rules?rule=" + fixedRuleDescription))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .DELETE()
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
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
            HttpRequest request = HttpRequest.newBuilder(defaultURI.resolve("/members?member=" + member))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .DELETE()
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
                return beerMain2;
            } else {
                return beerMain;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public BeerMain payPunishment(BeerMain beerMain, String member, String description, int value) {
        String putMappingPath = "/payPunishment?";
        String key1 = "member=";
        String descriptionPath = "&description=";
        String valuePath = "&value=";
        String memberValue = String.valueOf(value);
        String fixedDescription = replaceSpace(description);
        try {
            HttpRequest request = HttpRequest.newBuilder(defaultURI.resolve(
                    putMappingPath + key1 + member + descriptionPath + fixedDescription
                    + valuePath + memberValue))
                    .header(ACCEPT_HEADER, APPLICATION_JSON)
                    .DELETE()
                    .build();
            final HttpResponse<String> response =
                    HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
            if (response.body() != null) {
                BeerMain beerMain2 = getBeermain();
                return beerMain2;
            } else {
                return beerMain;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
