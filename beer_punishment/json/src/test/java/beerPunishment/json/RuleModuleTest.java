package beerPunishment.json;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;


public class RuleModuleTest {

    //{"items":[{"rule":"Komme for sent","value":2},{"rule":"Ikke gjort TODO","value":3}]}

    private static ObjectMapper mapper;
    private final static String ruleListTwoItems = "{\"rules\":[{\"rule\":\"Komme for sent\",\"value\":2},{\"rule\":\"Ikke gjort TODO\",\"value\":3}],\"memberRuleViolations\":[]}";

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
                    ruleListTwoItems,
                    mapper.writeValueAsString(list));
        }catch (JsonProcessingException e){
            fail();
        }

    }

    static void checkRule(Rule rule, String description, int value){
        assertEquals(description, rule.getDescription());
        assertEquals(value, rule.getPunishmentValue());
    }

    static void checkRule(Rule rule1, Rule rule2){
        checkRule(rule1, rule2.getDescription(), rule2.getPunishmentValue());
    }

    @Test
    public void testDeserializers(){
        try {
            BeerMain beerMain =  mapper.readValue(ruleListTwoItems, BeerMain.class);
            Iterator<Rule> rule = beerMain.iterator();
            assertTrue(rule.hasNext());
            checkRule(rule.next(),"Komme for sent",2);
            assertTrue(rule.hasNext());
            checkRule(rule.next(),"Ikke gjort TODO",3);
            assertFalse(rule.hasNext());

        } catch (JsonProcessingException e) {
            fail();
        }
    }


    @Test
    public void testSerializersDeserializers() {
        BeerMain list = new BeerMain();
        Rule rule1 = new Rule("Komme for sent",2);
        Rule rule2 = new Rule("Ikke gjort TODO",3);
        list.addRule(rule1);
        list.addRule(rule2);
        try {
            String json = mapper.writeValueAsString(list);
            BeerMain beerMain2 =  mapper.readValue(json, BeerMain.class);
            Iterator<Rule> r = beerMain2.iterator();
            assertTrue(r.hasNext());
            checkRule(r.next(),rule1);
            assertTrue(r.hasNext());
            checkRule(r.next(),rule2);
            assertFalse(r.hasNext());
        }catch (JsonProcessingException e){
            fail();
        }
    }

}