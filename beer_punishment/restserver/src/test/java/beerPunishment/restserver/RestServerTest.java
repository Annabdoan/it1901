package beerPunishment.restserver;

import beerPunishment.restserver.BeerMainRestController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BeerMainRestController.class)
@ContextConfiguration(classes ={BeerMainRestController.class, BeerMainService.class, RestServerApp.class })
class BeerMainRestIntControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getBeerMain() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/beerMain");
        MvcResult result = mvc.perform(request).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals("{\"rules\":[],\"memberRuleViolations\":{},\"usernames\":[]}",
                result.getResponse().getContentAsString());
    }

    @Test
    void addMember() {

    }

    @Test
    void deleteMember() {
    }

    @Test
    void addRule() {
    }

    @Test
    void removeRule() {
    }

    @Test
    void punishMember() {
    }

    @Test
    void payPunishment() {
    }

    @Test
    void ping() {
    }
}
