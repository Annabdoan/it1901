package beerPunishment.restserver;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes ={BeerMainRestController.class, BeerMainService.class, RestServerApp.class })
//@ContextConfiguration(locations = "classpath:WEB-INF/applicationContext.xml")


@WebMvcTest
public class RestServerTest {

    @Autowired
    private MockMvc mockMvc;

   /* @MockBean
    private BeerMainService beerMainService;*/

    private JsonHandler jsonHandler;
   // private BeerMainRestController beerMainRestController = new BeerMainRestController();

   @BeforeAll
    public void setup() throws IllegalStateException, IOException {
       jsonHandler = new JsonHandler();

       final BeerMain beerMain = BeerMainService.createBeerMain();
       final Rule rule = new Rule("Banne", 3);
       beerMain.addRule(rule);
       beerMain.addMember("Sara");

       //beerMainRestController.writeToJson();

   }

   @Test
    public void testGetBeerMain() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get("/beerMain")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andReturn();
   }

}
