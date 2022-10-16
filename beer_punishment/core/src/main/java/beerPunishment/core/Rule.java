package beerPunishment.core;

public class Rule {

    private String description;
    private int punishmentValue;

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

    private static boolean isValidPunishmentValue(int punishmentValue) {
        if (punishmentValue < 1) {
            return false;
        }
        return true;
    }

    private static boolean isValidDescription(String description) {
        if (description.isEmpty()) {
            return false;
        }
        return true;
    }


    public Rule() {

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPunishmentValue(int value) {
        this.punishmentValue = value;
    }


    public String getDescription() {
        return description;
    }

    public int getPunishmentValue() {
        return punishmentValue;
    }


    public String toString() {
        return String.format("[Rule rule=%s value=%s]", getDescription(), getPunishmentValue());
        //return description + "\t\t\t" + punishmentValue ;
    }

    public String toStringDisplayFormat() {
        return description + "\t\t\t" + punishmentValue;
    }
}