package beerPunishment.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RuleModuleTest {

    //{"items":[{"rule":"Komme for sent","value":2},{"rule":"Ikke gjort TODO","value":3}]}

    private static ObjectMapper mapper;

    @BeforeAll
    public static void setUp(){
        mapper = new ObjectMapper();
        mapper.registerModule(new RuleModule());
    }

    @Test
    public void testSerializers(){

        BeerMain list = new BeerMain();
        Rule rule = new Rule("Komme for sent",2);
        Rule rule2 = new Rule("Ikke gjort TODO",3);
        list.addRule(rule);
        list.addRule(rule2);
        try {
            assertEquals(
        "{\"rules\":[{\"rule\":\"Komme for sent\",\"value\":2},{\"rule\":\"Ikke gjort TODO\",\"value\":3}]}",
                mapper.writeValueAsString(list));
        }catch (JsonProcessingException e){
            fail();
        }

    }

}
