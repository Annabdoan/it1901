package beerPunishment.json;

import beerPunishment.core.Rule;
import beerPunishment.core.BeerMain;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

//making the list of rule objects to json
public class BeerMainSerializer extends JsonSerializer<BeerMain>{
    /*
    format:
    {
        "rules": [ ... ],
    }
     */

    @Override
    public void serialize(BeerMain list, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        jGen.writeStartObject(); //generate the start
        jGen.writeArrayFieldStart("rules");
        for (Rule rule : list) {
            jGen.writeObject(rule);
        }
        jGen.writeEndArray();
        jGen.writeStringField("memberRuleViolations", mapper.writeValueAsString(list.getMemberRuleViolations()));
        jGen.writeEndObject(); //gir sluttkurven
    }

}