package beerPunishment.json;

import beerPunishment.core.Rule;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import beerPunishment.core.Rule;
import beerPunishment.core.BeerMain;


public class BeerMainSerializer extends JsonSerializer <Rule>{
    /*
    format:
    {
        "items": [ ... ],
    }
     */
    public void serialize(BeerMain list, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject(); //generate the start
        jGen.writeArrayFieldStart("items");
        for (Rule rule : list) {

        }
        jGen.writeStringField("rule", rule.getDescription());
        jGen.writeIntegerField("value", rule.getPunishmentValue()); //dobbeltsjekk om integer
        jGen.writeEndObject(); //gir sluttkurven
    }
}