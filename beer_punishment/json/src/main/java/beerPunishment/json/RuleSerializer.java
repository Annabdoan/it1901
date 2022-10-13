package beerPunishment.json;


import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import beerPunishment.core.Rule;


public class RuleSerializer extends JsonSerializer<Rule> {
    /*
    format:
    {
        "rule": "...",
        "value": int
    }
     */

    //help us serialize rules
    public void serialize(Rule rule, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException{
        jGen.writeStartObject(); //generate the start of the json object
        jGen.writeStringField("rule", rule.getDescription());
        jGen.writeNumberField("value", rule.getPunishmentValue());
        jGen.writeEndObject(); //gir sluttkurven
    }
}