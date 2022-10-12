package beerPunishment.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BeerMain implements Iterable<Rule>{
    
    private List<Rule> rules = new ArrayList<>();

    public BeerMain() {
    }

    public List<Rule> getRules() {
        return new ArrayList<>(this.rules);
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public void removeRule(Rule rule) {
        this.rules.remove(rule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return rules.iterator();
    }

    @Override
    public String toString() {
        return "BeerMain{" +
                "rules=" + rules +
                '}';
    }
}