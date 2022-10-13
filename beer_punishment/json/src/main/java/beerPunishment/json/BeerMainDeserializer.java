package beerPunishment.json;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;

import java.io.IOException;

public class BeerMainDeserializer extends JsonDeserializer<BeerMain> {

    private RuleDeserializer ruleDeserializer = new RuleDeserializer();

    /*
    format:
    {
        "rules": [ ... ],
    }
     */

    @Override
    public BeerMain deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        if (treeNode instanceof ObjectNode){
            ObjectNode objectNode = (ObjectNode) treeNode;
            BeerMain beerMainList = new BeerMain();
            JsonNode rulesNode = objectNode.get("rules");
            if (rulesNode instanceof ArrayNode){
                for (JsonNode elementNode: (ArrayNode) rulesNode){
                    Rule rule = ruleDeserializer.deserialize(elementNode);
                    if (rule != null){
                        beerMainList.addRule(rule);
                    }
                }
            }
            return beerMainList;
        }
        return null;
    }
}
