package beerPunishment.json;

import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;


/**
 * Class to serialize {@link Rule}.
 */
public class RuleSerializer extends JsonSerializer<Rule> {
    /*
    format:
    {
        "rule": "...",
        "value": int
    }
     */


    /**
     * Serialize Rules.
     */
    public void serialize(Rule rule, JsonGenerator jGen,
                          SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject(); //generate the start of the json object
        jGen.writeStringField("rule", rule.getDescription());
        jGen.writeNumberField("value", rule.getPunishmentValue());
        jGen.writeEndObject(); //gir sluttkurven
    }
}