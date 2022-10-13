package beerPunishment.json;

import beerPunishment.core.Rule;
import beerPunishment.core.BeerMain;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

//making the list of rule objects to json
public class BeerMainSerializer extends JsonSerializer<BeerMain>{
    /*
    format:
    {
        {"rules": [ ... ],"memberRuleViolations:"{\"username\"[{"description":"...","punishmentValue":...}, ...]}}
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
        //jGen.writeStringField("memberRuleViolations", mapper.writeValueAsString(list.getMemberRuleViolations()));
        jGen.writeArrayFieldStart("memberRuleViolations");
        for (Map.Entry<String, List<Rule>> violations : list.getMemberRuleViolations().entrySet()){
            jGen.writeObject(violations.getKey());
            jGen.writeObject(violations.getValue());
        }
        jGen.writeEndArray();
        jGen.writeEndObject(); //gir sluttkurven
    }

}