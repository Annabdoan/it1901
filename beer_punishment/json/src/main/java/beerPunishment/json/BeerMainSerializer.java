package beerPunishment.json;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to serialize {@link BeerMain}.
 */
public class BeerMainSerializer extends JsonSerializer<BeerMain> {
    /*
    format:
    {
        {"rules": [ ... ],
        "memberRuleViolations:"{\"username\"[{"description":"...","punishmentValue":...}, ...]}}
    }
     */

    @Override
    public void serialize(BeerMain list, JsonGenerator jGen,
                          SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject(); //generate the start {
        jGen.writeArrayFieldStart("rules");
        for (Rule rule : list) {
            jGen.writeObject(rule);
        }
        jGen.writeEndArray();
        jGen.writeArrayFieldStart("memberRuleViolations");
        for (Map.Entry<String, List<Rule>> violations : list.getMemberRuleViolations().entrySet()) {
            jGen.writeObject(violations.getKey());
            jGen.writeObject(violations.getValue());
        }
        jGen.writeEndArray();
        jGen.writeEndObject(); //generate end }
    }

}