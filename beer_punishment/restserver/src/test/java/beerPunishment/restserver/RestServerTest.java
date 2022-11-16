package beerPunishment.restserver;

import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@ExtendWith(SpringExtension.class)
@WebMvcTest(BeerMainRestController.class)
@ContextConfiguration(classes ={BeerMainRestController.class, BeerMainService.class, RestServerApp.class })
class BeerMainRestIntControllerTest {

    @Autowired
    private MockMvc mvc;


    @Order(1)
    @Test
    void ping() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/ping");
        MvcResult result = mvc.perform(request).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals("pong",
                result.getResponse().getContentAsString());
    }
    @Order(2)
    @Test
    void getBeerMain() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/beerMain");
        MvcResult result = mvc.perform(request).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals("{\"rules\":[],\"memberRuleViolations\":{},\"usernames\":[]}",
                result.getResponse().getContentAsString());
    }
    @Order(2)
    @Test
    void addMember() throws Exception{
        Gson gson = new Gson();
        mvc.perform( MockMvcRequestBuilders
                        .post("/members?name=Maurice")
                        .content(gson.toJson("Maurice"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        RequestBuilder request = MockMvcRequestBuilders.get("/beerMain");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"rules\":[],\"memberRuleViolations\":{\"Maurice\":[]},\"usernames\":[\"Maurice\"]}",
                result.getResponse().getContentAsString());
    }
    @Order(3)
    @Test
    void addRule() throws Exception {
        Gson gson = new Gson();
        mvc.perform( MockMvcRequestBuilders
                        .post("/rules?description=Banne&value=2")
                        .content(gson.toJson("Banne;2"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        RequestBuilder request = MockMvcRequestBuilders.get("/beerMain");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"rules\":[{\"description\":\"Banne\",\"punishmentValue\":2}],\"memberRuleViolations\":{\"Maurice\":[]},\"usernames\":[\"Maurice\"]}",
                result.getResponse().getContentAsString());
    }
    @Order(4)
    @Test
    void punishMember() throws Exception {
        Gson gson = new Gson();
        mvc.perform( MockMvcRequestBuilders
                        .put("/punishMember?member=Maurice&description=Banne&value=2")
                        .content(gson.toJson("Maurice;Banne;2"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        RequestBuilder request = MockMvcRequestBuilders.get("/beerMain");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"rules\":[{\"description\":\"Banne\",\"punishmentValue\":2}],\"memberRuleViolations\":{\"Maurice\":[{\"description\":\"Banne\",\"punishmentValue\":2}]},\"usernames\":[\"Maurice\"]}",
                result.getResponse().getContentAsString());
    }

    @Order(5)
    @Test
    void payPunishment() throws Exception {
        Gson gson = new Gson();
        mvc.perform( MockMvcRequestBuilders
                        .delete("/payPunishment?member=Maurice&description=Banne&value=2")
                        .content(gson.toJson("Maurice;Banne;2"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        RequestBuilder request = MockMvcRequestBuilders.get("/beerMain");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"rules\":[{\"description\":\"Banne\",\"punishmentValue\":2}],\"memberRuleViolations\":{\"Maurice\":[]},\"usernames\":[\"Maurice\"]}",
                result.getResponse().getContentAsString());
    }

    @Order(6)
    @Test
    void deleteMember() throws Exception {
        Gson gson = new Gson();
        mvc.perform( MockMvcRequestBuilders
                        .delete("/members?member=Maurice")
                        .content(gson.toJson("Maurice"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        RequestBuilder request = MockMvcRequestBuilders.get("/beerMain");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"rules\":[{\"description\":\"Banne\",\"punishmentValue\":2}],\"memberRuleViolations\":{},\"usernames\":[]}",
                result.getResponse().getContentAsString());
    }
    @Order(7)
    @Test
    void deleteRule() throws Exception {
        Gson gson = new Gson();
        mvc.perform( MockMvcRequestBuilders
                        .delete("/rules?rule=Banne")
                        .content(gson.toJson("Banne"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        RequestBuilder request = MockMvcRequestBuilders.get("/beerMain");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"rules\":[],\"memberRuleViolations\":{},\"usernames\":[]}",
                result.getResponse().getContentAsString());
    }


}
