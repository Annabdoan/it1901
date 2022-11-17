package beerPunishment.core;

/**
 * Rules in a rulelist in BeerMain.
 */
public class Rule {

    private String description;
    private int punishmentValue;

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

    /**
     * Empty constructor needed for test.
     */
    public Rule() {

    }

    public String getDescription() {
        return description;
    }

    public int getPunishmentValue() {
        return punishmentValue;
    }

    public Boolean equals(Rule r2) {
        return r2.getDescription().equalsIgnoreCase(this.getDescription());
    }

    public String toString() {
        return String.format("[Rule rule=%s value=%s]", getDescription(), getPunishmentValue());
        //return description + "\t\t\t" + punishmentValue ;
    }

    public String toStringDisplayFormat() {
        return description + "\t\t\t" + punishmentValue;
    }
}