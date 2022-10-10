package beerPunishment.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BeerMain {
    
    private List<Rule> rules = new ArrayList<>();
    private HashMap<String, List<Rule>> memberRuleViolations = new HashMap<String, List<Rule>>();

    public BeerMain() {
    }

    public List<Rule> getRules() {
        return new ArrayList<>(this.rules);
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public void addMember(String username) {
        if (memberRuleViolations.containsKey(username)) {
            throw new IllegalArgumentException("Brukernavnet eksisterer allerede");
        }
        memberRuleViolations.put(username, new ArrayList<>());
    }

    public void punishMember(String username, Rule rule) {
        if (!memberRuleViolations.containsKey(username)){
            throw new IllegalArgumentException("Brukernavnet eksisterer ikke");
        }
        List<Rule> violations = memberRuleViolations.get(username);
        violations.add(rule);
        memberRuleViolations.put(username, violations);
    }
}