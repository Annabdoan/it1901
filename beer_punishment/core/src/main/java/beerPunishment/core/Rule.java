package beerPunishment.core;

/**
 * Rules in a rulelist in BeerMain.
 */
public class Rule {

    private String description;
    private int punishmentValue;

    /**
     * Empty constructor needed for test.
     */
    public Rule() {

    }

    /**
     * Creates Rule with all relevant parameters.
     *
     * @param description     description of rule
     * @param punishmentValue value of the punishment
     */
    public Rule(String description, int punishmentValue) {
        if (!isValidDescription(description)) {
            throw new IllegalArgumentException("Invalid description.");
        }
        if (!isValidPunishmentValue(punishmentValue)) {
            throw new IllegalArgumentException("Invalid punishmentvalue.");
        }
        this.description = description;
        this.punishmentValue = punishmentValue;
    }

    /**
     * Checks if punishment is a negative value.
     *
     * @param punishmentValue value of the punishment
     */
    private static boolean isValidPunishmentValue(int punishmentValue) {
        if (punishmentValue < 1) {
            return false;
        }
        return true;
    }

    /**
     * Checks if description is empty.
     *
     * @param description the description of the rule
     */
    private static boolean isValidDescription(String description) {
        if (description.isEmpty()) {
            return false;
        }
        return true;
    }

    public String getDescription() {
        return description;
    }

    public int getPunishmentValue() {
        return punishmentValue;
    }

    /**
     * A simple equals method that compares two Rules based only on the description.
     *
     * @param r2 The rule to compare with this rule.
     *
     * @return Returns a boolean. True if equal, false else.
     */
    public Boolean equals(Rule r2) {
        return r2.getDescription().equalsIgnoreCase(this.getDescription());
    }

    public String toString() {
        return String.format("[Rule rule=%s value=%s]", getDescription(), getPunishmentValue());
    }

    /**
     * A seperate toString method used to generate a prettier print that is used in the UI.
     *
     * @return The rule in a "pretty print"  format.
     */
    public String toStringDisplayFormat() {
        return description + "\t\t\t" + punishmentValue;
    }
}