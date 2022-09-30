package beerPunishment.json;


import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RuleModule extends SimpleModule {
    private static final String NAME = "RuleModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

    public RuleModule() {
        super(NAME, VERSION_UTIL.version());
        addSerializer(Rule.class, new RuleSerializer());
        addSerializer(BeerMain.class, new BeerMainSerializer());

    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new RuleModule());
        BeerMain list = new BeerMain();
        Rule rule = new Rule("Komme for sent",2);
        Rule rule2 = new Rule("Ikke gjort TODO",3);
        list.addRule(rule);
        list.addRule(rule2);
        try {
            System.out.println(mapper.writeValueAsString(list));
        }catch (JsonProcessingException e){
            System.err.println("Virket ikke");
            e.printStackTrace();
        }
    }

}