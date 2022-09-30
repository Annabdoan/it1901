package beerPunishment.json;

import beerPunishment.core.Rule;
import beerPunishment.core.BeerMain;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import beerPunishment.core.Rule;
import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;


public class BeerMainSerializer extends JsonSerializer<BeerMain>{
    /*
    format:
    {
        "items": [ ... ],
    }
     */

    @Override
    public void serialize(BeerMain list, JsonGenerator jGen, SerializerProvider serializerProvider) throws IOException {
        jGen.writeStartObject(); //generate the start
        jGen.writeArrayFieldStart("items");
        for (Rule rule : list) {
            jGen.writeObject(rule);
        }
        jGen.writeEndArray();
        jGen.writeEndObject(); //gir sluttkurven
    }


}