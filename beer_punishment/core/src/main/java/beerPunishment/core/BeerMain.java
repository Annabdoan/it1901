package beerPunishment.core;

import java.util.*;

/**
 * A class for rule lists without items and hashmaps without items.
 * Can be used as placeholder for real beer app instances.
 */
public class BeerMain implements Iterable<Rule> {

    private List<Rule> rules = new ArrayList<>();
    private HashMap<String, List<Rule>> memberRuleViolations = new HashMap<String, List<Rule>>();


    public BeerMain() {
    }

    /**
     * Access method for rules.
     *
     * @return the rules
     */
    public List<Rule> getRules() {
        return new ArrayList<>(this.rules);
    }

    /**
     * Adds new rule to this list of rules.
     *
     * @param rule the rule
     */
    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    /**
     * Removes already existing User.
     */
    public void removeRule(Rule rule) {
        this.rules.remove(rule);
    }

    /**
     * Method for adding members.
     *
     * @param username of the member to add
     */
    public void addMember(String username) {
        if (memberRuleViolations.containsKey(username)) {
            throw new IllegalArgumentException("Brukernavnet eksisterer allerede");
        }
        memberRuleViolations.put(username, new ArrayList<>());
    }

    /**
     * Method for punishing members.
     *
     * @param username the name the member to punish
     * @param rule     the rule that the member has broken
     */
    public void punishMember(String username, Rule rule) {
        if (!memberRuleViolations.containsKey(username)) {
            throw new IllegalArgumentException("Brukernavnet eksisterer ikke");
        }
        List<Rule> violations = memberRuleViolations.get(username);
        violations.add(rule);
        memberRuleViolations.put(username, violations);
    }

    private HashMap<String, Integer> generateMembersPunishments() {
        HashMap<String, Integer> punishmentStatus = new HashMap<>();
        for (Map.Entry<String, List<Rule>> entry : memberRuleViolations.entrySet()) {
            Integer totalpunishmemt = 0;
            for (Rule rule : memberRuleViolations.get(entry.getKey())
            ) {
                totalpunishmemt += rule.getPunishmentValue();
            }
            punishmentStatus.put(entry.getKey(), totalpunishmemt);
        }
        /*
        for (String username : memberRuleViolations.keySet()
        ) {
            Integer totalpunishmemt = 0;
            for (Rule rule : memberRuleViolations.get(username)
            ) {
                totalpunishmemt += rule.getPunishmentValue();
            }
            punishmentStatus.put(username, totalpunishmemt);

        }*/
        return new HashMap<String, Integer>(punishmentStatus);
    }

    /**
     * Access method for members.
     *
     * @return the members.
     */
    public List<String> getUsernames() {
        return new ArrayList<>(memberRuleViolations.keySet());
    }

    /**
     * Access method for all the rule violations .
     *
     * @return the hashmap of members and what rules they have broken.
     */
    public HashMap<String, List<Rule>> getMemberRuleViolations() {
        return new HashMap<String, List<Rule>>(this.memberRuleViolations);
    }

    public void setMemberRuleViolations(HashMap<String, List<Rule>> memberRuleViolations) {
        this.memberRuleViolations = memberRuleViolations;
    }

    /**
     * Makes a ToString out of the hashmap.
     */
    public List<String> generatePunishmentStatusToString() {
        HashMap<String, Integer> punishmentStatus = generateMembersPunishments();
        List<String> punishmentToString = new ArrayList<>();
        for (Map.Entry<String, List<Rule>> entry : memberRuleViolations.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey());
            sb.append("\t\t\t\t\t");
            sb.append(punishmentStatus.get(entry.getKey()));
            punishmentToString.add(sb.toString());
        }
        /*
        for (String username : punishmentStatus.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(username);
            sb.append("\t\t\t\t\t");
            sb.append(punishmentStatus.get(username));
            punishmentToString.add(sb.toString());
        }*/
        return new ArrayList<>(punishmentToString);
    }

    /**
     * Iterator to easilly move between objects in list.
     *
     * @return iterator of rules
     */
    @Override
    public Iterator<Rule> iterator() {
        return rules.iterator();
    }


    // Iterator
    //public Iterator<Map.Entry<String, List<Rule>>> new_Iterator = memberRuleViolations.entrySet().iterator();

    /**
     * Iterator to easilly move between objects in list.
     *
     * @return iterator of members and their violations
     */
    public Iterator<Map.Entry<String, List<Rule>>> violationIterator() {
        return memberRuleViolations.entrySet().iterator();
    }


    @Override
    public String toString() {
        return "BeerMain{"
                + "rules="
                + rules
                + ", memberRuleViolations="
                + memberRuleViolations
                + '}';
    }
}