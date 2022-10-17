package beerPunishment.json;

import beerPunishment.core.BeerMain;
import beerPunishment.core.Rule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to Deserialize {@link BeerMain}.
 */
public class BeerMainDeserializer extends JsonDeserializer<BeerMain> {

    private RuleDeserializer ruleDeserializer = new RuleDeserializer();

    /*
    format:
    {
        {"rules": [ ... ],
        "memberRuleViolations:"{\"username\"[{"description":"...","punishmentValue":...}, ...]}}
    }
     */

    @Override
    public BeerMain deserialize(JsonParser parser,
                                DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        if (treeNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) treeNode;
            BeerMain beerMainList = new BeerMain();
            JsonNode rulesNode = objectNode.get("rules");
            if (rulesNode instanceof ArrayNode) {
                for (JsonNode elementNode : (ArrayNode) rulesNode) {
                    Rule rule = ruleDeserializer.deserialize(elementNode);
                    if (rule != null) {
                        beerMainList.addRule(rule);
                    }
                }
            }
            JsonNode violationNode = objectNode.get("memberRuleViolations");
            HashMap<String, List<Rule>> tempMap = new HashMap<String, List<Rule>>();
            String key = "";
            if (violationNode instanceof ArrayNode) {
                int i = 0;
                for (JsonNode elementNode : (ArrayNode) violationNode) {
                    if (i % 2 == 0) {
                        key = elementNode.toString();
                        tempMap.put(key, new ArrayList<>());
                        i++;
                    } else {
                        List<Rule> tempList = new ArrayList<>();
                        for (JsonNode temp : elementNode) {
                            Rule rule = ruleDeserializer.deserialize(temp);
                            if (rule != null) {
                                tempList.add(rule);
                            }
                        }
                        tempMap.put(key, tempList);
                        i++;
                    }
                }
                beerMainList.setMemberRuleViolations(tempMap);
            }
            return beerMainList;
        }
        return null;
    }
}
