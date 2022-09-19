package beerPunishment;

import java.util.ArrayList;
import java.util.List;

public class BeerMain {
    
    private List<Rule> rules = new ArrayList<>();

    public BeerMain() {
    }

    public List<Rule> getRules() {
        return new ArrayList<>(this.rules);
    }
}