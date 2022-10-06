package beerPunishment.json;


import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.EnumSet;
import java.util.Set;

//en modulen som kongiguerer et sett med serilizerse som
public class RuleModule extends SimpleModule {
    private static final String NAME = "RuleModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

    public RuleModule(Set<RulePersistence.RuleModelParts> parts) {
        super(NAME, VERSION_UTIL.version());
        addSerializer(Rule.class, new RuleSerializer());
        addSerializer(BeerMain.class, new BeerMainSerializer());
        addDeserializer(Rule.class, new RuleDeserializer());
        addDeserializer(BeerMain.class, new BeerMainDeserializer());
    }

    public RuleModule() {
        this(EnumSet.allOf(RulePersistence.RuleModelParts.class));
    }


    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new RuleModule());
        BeerMain list = new BeerMain();
        Rule rule1 = new Rule();
        rule1.setDescription("Komme for sent");
        Rule rule2 = new Rule();
        rule2.setDescription("Ikke gjort TODO");
        rule2.setPunishmentValue(5);
        list.addRule(rule1);
        list.addRule(rule2);
        try {
            String json = mapper.writeValueAsString(list);
            BeerMain beerMainList2 = mapper.readValue(json, BeerMain.class);
            for (Rule rule: beerMainList2){
                System.out.println(rule);
            }
        }catch (JsonProcessingException e){
            System.err.println("Virket ikke");
            e.printStackTrace();
        }
    }

}