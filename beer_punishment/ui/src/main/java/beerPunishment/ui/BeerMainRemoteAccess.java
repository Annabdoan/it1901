package beerPunishment.ui;

import beerPunishment.core.BeerMain;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class that centralizes access to a TodoModel.
 * Makes it easier to support transparent use of a REST API.
 */
public class BeerMainRemoteAccess implements IBeerMainAccess {

    private static final String APPLICATION_JSON = "application/json";

    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

    private static final String ACCEPT_HEADER = "Accept";

    private static final String CONTENT_TYPE_HEADER = "Content-Type";


    //denne må være localhost:8080/
    public BeerMainRemoteAccess() {

    }


    private static final URI defaultURI = URI.create("http://localhost:8080");


    /**
     * Method for replacing whitespaces with %20 for URL.
     *
     * @param input the input string that need to be fixed
     */
    private String replaceSpace(String input) {
        String fixedInput = input.replaceAll("\\s", "%20");
        return fixedInput;
    }

    /**
     * Gets a connection to the server if it exists.
     *
     * @param baseUri the URI of the server to ping.
     */
    public static Boolean pingServer(URI baseUri) {

        HttpRequest request = HttpRequest.newBuilder(baseUri.resolve("/ping"))
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

    /**
     * Sends a GET-request to fetch a beerMain object from the
     * server.
     */
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


    /**
     * Sends a POST-request and adds a rule to the beerMain.
     *
     * @param beerMain    the beerMain
     * @param description the description of the rule
     * @param value       the value of the rule
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

    /**
     * Sends a POST-request and adds a member to the beerMain.
     *
     * @param beerMain the beerMain
     * @param name     the name of member
     */
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
     * Sends a PUT-request and punish the member.
     *
     * @param beerMain    the beerMain
     * @param member      the member to punish
     * @param description the description of the rule
     * @param value       the value of the rule
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
     * Sends a DELETE-request and removes the rule with the given description from the underlying beerMain.
     *
     * @param beerMain the beerMain
     * @param ruleDescription the description of the rule to remove
     */
    public BeerMain deleteRule(BeerMain beerMain, String ruleDescription) {
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

    /**
     * Sends a DELETE-request and removes a member from the underlying beerMain.
     *
     * @param beerMain the beerMain
     * @param member the member
     */
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

    /**
     * Sends a DELETE-request and removes a punishment from a member.
     *
     * @param beerMain    the beerMain
     * @param member      the member
     * @param description the description of the rule
     * @param value       the value of the rule
     */
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
