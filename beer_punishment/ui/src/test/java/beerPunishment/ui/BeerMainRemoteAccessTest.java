package beerPunishment.ui;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;
import java.net.URISyntaxException;

import static beerPunishment.ui.BeerController.defaultURI;
import static org.junit.Assert.assertTrue;

public class BeerMainRemoteAccessTest {

    private WireMockConfiguration wmConfig;
    private WireMockServer wmServer;

    private BeerMainRemoteAccess beerMainRemoteAccess;

    @BeforeEach void setup() throws URISyntaxException {
        wmConfig = WireMockConfiguration.wireMockConfig().port(8080);
        wmServer = new WireMockServer(wmConfig.portNumber());
        wmServer.start();
        WireMock.configureFor("localhost", wmConfig.portNumber());
        BeerController beerController = new BeerController();
        URI defaultURI = URI.create("http://localhost:8080"); //Må lage getter i BeerController!!
        beerMainRemoteAccess = new BeerMainRemoteAccess(defaultURI);

        BeerMain beerMain = new BeerMain();
        final Rule rule = new Rule("Banne", 3);
        beerMain.addRule(rule);
        beerMain.addMember("Sara");
    }

    @Test
    public void testWireMock() {
        assertTrue(wmServer.isRunning());
    }

    @Test
    public void testPingServer() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("http://localhost:8080/ping"))
                .withHeader("Accept", WireMock.equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("pong"))

        );
    }

    @Test //Når ikke getBeerMain!
    public void testGetBeerMain() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("http://localhost:8080/beerMain"))
                .withHeader("Accept", WireMock.equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"rules\":[],\"memberRuleViolations\":{}}"))

        );

    }

    @Test
    public void testAddRule() {
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/rules?description=TestRule&value=1"))
                .withHeader("Accept", WireMock.equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"rules\":[\"description\": \"TestRule\",\"punishmentValue\": 1],\"memberRuleViolations\":{}}"))
        );
    }

    @Test
    public void testAddMember() {
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/members/name?name=TestMember"))
                .withHeader("Accept", WireMock.equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"rules\":[],\"memberRuleViolations\":{\"TestMember\":[]},\"usernames\":[\"TestMember\"]}"))

        );
    }

    @Test
    public void testPunishMember() {
        WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/punishMember?member=TestMember&description=TestRule&value=1"))
                .withHeader("Accept", WireMock.equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"rules\":[{\"description\":\"TestRule\",\"punishmentValue\":1}],\"memberRuleViolations\":{\"TestMember\":[{\"description\":\"TestRule\",\"punishmentValue\":1}]},\"usernames\":[\"TestMember\"]}"))
        );
    }

    @Test
    public void testRemoveRule() {

        //Adding the rule first
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/rules?description=TestRule&value=1"))
                .withHeader("Accept", WireMock.equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"rules\":[\"description\": \"TestRule\",\"punishmentValue\": 1],\"memberRuleViolations\":{}}"))
        );

        //Deleting the rule that was just created
        WireMock.stubFor(WireMock.delete(WireMock.urlEqualTo("/rules?description=TestRule"))
                .withHeader("Accept", WireMock.equalTo("application/json"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"rules\":[],\"memberRuleViolations\":{}}"))
        );
    }


   private String getUrl(String... segments) {
        String url = "/beerMain";
        for (String segment : segments) {
            url = url + "/" + segment;
        }
        return url;
    }

    @AfterEach
    public void stopWireMockServer() {
        wmServer.stop();
    }

}
