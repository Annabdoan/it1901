package beerPunishment.json;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;

/**
 * Class to Deserialize {@link Rule}.
 */
public class RuleDeserializer extends JsonDeserializer<Rule> {


    @Override
    public Rule deserialize(JsonParser parser,
                            DeserializationContext deserializationContext) throws IOException, IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    /**
     * Deserialize Rule.
     */
    public Rule deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Rule rule = new Rule();
            JsonNode textNode = objectNode.get("rule");
            if (textNode instanceof TextNode) {
                rule.setDescription(((TextNode) textNode).asText());
            }
            JsonNode valueNode = objectNode.get("value");
            if (valueNode instanceof NumericNode) {
                rule.setPunishmentValue(((NumericNode) valueNode).intValue());
            }
            return rule;
        }
        return null;
    }


}
