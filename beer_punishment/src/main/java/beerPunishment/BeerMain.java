package beerPunishment;

import java.util.ArrayList;
import java.util.List;

public class BeerMain {
    
    private List<Rule> rules = new ArrayList<>();

    public BeerMain(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Rule> getRules() {
        return rules;
    }
}