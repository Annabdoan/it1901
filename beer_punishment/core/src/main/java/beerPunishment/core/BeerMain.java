package beerPunishment.core;

import java.util.*;

/**
 * A class that tracks rules that applies to a group.
 * Can be used as placeholder for real beer app instances.
 */
public class BeerMain implements Iterable<Rule> {

    private Collection<Rule> rules = new ArrayList<>();
    private HashMap<String, Collection<Rule>> memberRuleViolations = new HashMap<>();

    public BeerMain() {
    }

    /**
     * Access method for rules.
     *
     * @return the rules
     */
    public Collection<Rule> getRules() {
        return new ArrayList<>(this.rules);
    }

    /**
     * Adds new rule to this list of rules.
     *
     * @param rule the rule
     */
    public void addRule(Rule rule) {
        for (Rule r1 : this.rules) {
            if (r1.equals(rule)) {
                throw new IllegalArgumentException("Ikke lov å lage samme regel to ganger");
            }
        }
        this.rules.add(rule);
    }

    /**
     * Sets the list of rules that apply to the BeerMain system
     *
     * @param rules the list of rules.
     */
    private void setRules(Collection<Rule> rules) {
        this.rules = rules;
    }

    /**
     * Removes already existing User.
     */
    public void deleteRule(Rule rule) {
        if (!rules.contains(rule)) {
            throw new IllegalArgumentException("Regelen eksisterer ikke");
        }
        this.rules.remove(rule);
    }

    /**
     * Method for removing rules using the description.
     *
     * @param description of the rule being deleted
     */
    public void deleteRuleUsingDescription(String description) {
        Rule empty = null;
        for (Rule rule : rules) {
            if (rule.getDescription().equals(description)) {
                empty = rule;
            }
        }
        if (empty == null) {
            throw new IllegalArgumentException("Regel eksisterer ikke");
        }
        rules.remove(empty);
    }

    /**
     * Method for checking if a member does not exist.
     *
     * @param username the name the member
     */
    public void checkValidUser(String username){
        if (!memberRuleViolations.containsKey(username)) {
            throw new IllegalArgumentException("Brukernavnet eksisterer ikke");
        }
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
     * Method for deleting existing members.
     *
     * @param username of the member to delete
     */
    public void deleteMember(String username) {
        checkValidUser(username);
        memberRuleViolations.remove(username);
    }

    /**
     * Method for punishing members.
     *
     * @param username the name the member to punish
     * @param rule     the rule that the member has broken
     */
    public void punishMember(String username, Rule rule) {
        checkValidUser(username);
        Collection<Rule> violations = memberRuleViolations.get(username);
        violations.add(rule);
        memberRuleViolations.put(username, violations);
    }

    /**
     * Access method for members.
     *
     * @return the members.
     */
    public Collection<String> getUsernames() {
        return new ArrayList<>(memberRuleViolations.keySet());
    }

    /**
     * Method for removing punishment from a member.
     *
     * @param username the name the member
     * @param rule     the rule that the member has broken and that is getting removed
     */
    public void deletePunishment(String username, Rule rule) {
        checkValidUser(username);
        int sizeBefore = memberRuleViolations.get(username).size();
        for (Rule tempRule : memberRuleViolations.get(username)) {
            if (rule.getDescription().equals(tempRule.getDescription())) {
                Collection<Rule> tempList = memberRuleViolations.get(username);
                tempList.remove(tempRule);
                memberRuleViolations.put(username, tempList);
                break;
            }
        }
        int sizeAfter = memberRuleViolations.get(username).size();
        if (sizeBefore == sizeAfter) {
            throw new IllegalArgumentException("Du har ikke brutt denne regelen");
        }
    }

    /**
     * Method for getting a list of a users violations.
     *
     * @param username the name the member to punish
     * @return ArrayList of the given members violations
     */
    public Collection<Rule> getMemberViolations(String username) {
        checkValidUser(username);
        return new ArrayList<>(memberRuleViolations.get(username));
    }

    /**
     * Makes a hashmap out of the list of given punishment status, member and number of penalties.
     *
     * @return hashmap of member with the associated number of penalties.
     */
    public HashMap<String, Integer> generateMembersPunishments() {
        HashMap<String, Integer> punishmentStatus = new HashMap<>();
        for (Map.Entry<String, Collection<Rule>> entry : memberRuleViolations.entrySet()) {
            Integer totalpunishmemt = 0;
            for (Rule rule : memberRuleViolations.get(entry.getKey())
            ) {
                totalpunishmemt += rule.getPunishmentValue();
            }
            punishmentStatus.put(entry.getKey(), totalpunishmemt);
        }
        return new HashMap<String, Integer>(punishmentStatus);
    }

    /**
     * Access method for all the rule violations .
     *
     * @return the hashmap of members and what rules they have broken.
     */
    public HashMap<String, Collection<Rule>> getMemberRuleViolations() {
        return new HashMap<>(this.memberRuleViolations);
    }

    public void setMemberRuleViolations(HashMap<String, Collection<Rule>> memberRuleViolations) {
        this.memberRuleViolations = new HashMap<>(memberRuleViolations);
    }

    /**
     * Iterator to easily move between objects in list.
     *
     * @return iterator of rules
     */
    @Override
    public Iterator<Rule> iterator() {
        return rules.iterator();
    }

    /**
     * Method for copying fields from a BeerMain into this BeerMain.
     *
     * @param bm the BeerMain that is copied
     */
    public void copy(BeerMain bm) {
        this.setMemberRuleViolations(bm.getMemberRuleViolations());
        this.setRules(bm.getRules());
    }

    /**
     * Makes a ToString out of the list of given punishment status.
     *
     * @return ArrayList of ToSting punishment status.
     */
    public Collection<String> generatePunishmentStatusToString() {
        HashMap<String, Integer> punishmentStatus = generateMembersPunishments();
        Collection<String> punishmentToString = new ArrayList<>();
        for (Map.Entry<String, Collection<Rule>> entry : memberRuleViolations.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey());
            sb.append("\t\t\t\t\t");
            sb.append(punishmentStatus.get(entry.getKey()));
            punishmentToString.add(sb.toString());
        }
        return new ArrayList<>(punishmentToString);
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
