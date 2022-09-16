package main.java.beerPunishment;

public class Rule {

    private String description;
    private int punishmentValue;


    public Rule(String description, int punishmentValue) {
        this.description = description;
        this.punishmentValue = punishmentValue;
    }


    public String getDescription() {
        return description;
    }

    public int getPunishmentValue() {
        return punishmentValue;
    }

    @overide
    public String toString() {
        return "[description=" + description + ", punishmentValue=" + punishmentValue + "]";
    }
}