package beerPunichment.restserver;

import beerPunishment.json.JsonHandler;
import beerPunishment.restserver.BeerMainRestController;
import beerPunishment.restserver.BeerMainService;
import beerPunishment.restserver.RestServerApp;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {BeerMainRestController.class , BeerMainService.class, RestServerApp.class })
@WebMvcTest
public class RestServerTest {

    @Autowired
    private MockMvc mockMvc;

    private JsonHandler jsonHandler;

   @BeforeAll
    public void setup() throws IllegalStateException, IOException {
       jsonHandler = new JsonHandler();


   }

}
