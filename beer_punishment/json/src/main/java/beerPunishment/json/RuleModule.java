package beerPunishment.json;


import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


/**
 * A Jackson module for configuring JSON serialization of BeerMain and Rule instances.
 */
public class RuleModule extends SimpleModule {
    private static final String NAME = "RuleModule";
    // private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

    /**
     * Initializes this RuleModule with appropriate serializers and deserializers.
     */
    public RuleModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(Rule.class, new RuleSerializer());
        addSerializer(BeerMain.class, new BeerMainSerializer());
        addDeserializer(Rule.class, new RuleDeserializer());
        addDeserializer(BeerMain.class, new BeerMainDeserializer());
    }
}