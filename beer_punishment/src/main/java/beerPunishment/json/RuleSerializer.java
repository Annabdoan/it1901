package beerPunishment.json;


import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import beerPunishment.core.Rule;


public class RuleSerializer extends JsonSerializer <Rule>{
    /*
    format:
    {
        "rule": "...",
        "value": int
    }
     */
    public void serialize(Rule rule, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException{
        jGen.writeStartObject(); //generate the start
        jGen.writeStringField("rule", rule.getDescription());
        jGen.writeIntegerField("value", rule.getPunishmentValue()); //dobbeltsjekk om integer
        jGen.writeEndObject(); //gir sluttkurven
    }
}