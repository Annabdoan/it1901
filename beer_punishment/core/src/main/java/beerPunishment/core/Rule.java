package beerPunishment.core;

public class Rule {

    private String description;
    private int punishmentValue;


    public Rule(String description, int punishmentValue) {
        this.description = description;
        this.punishmentValue = punishmentValue;
    }

    public Rule(){

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
}