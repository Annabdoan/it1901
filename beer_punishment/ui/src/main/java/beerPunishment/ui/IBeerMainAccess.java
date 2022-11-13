package beerPunishment.ui;

import beerPunishment.core.BeerMain;

/**
 * Interface for classes that wants to take advantage of a REST APi.
 */
public interface IBeerMainAccess {

    BeerMain getBeermain();

    /**
     * Adds a rule to the beerMain.
     *
     * @param beerMain    the beerMain
     * @param description the description of the rule
     * @param value       the value of the rule
     */
    BeerMain addRule(BeerMain beerMain, String description, int value);



    /**
     * Adds a member to the beerMain.
     *
     * @param beerMain the beerMain
     * @param name     the name of member
     */
    BeerMain addMember(BeerMain beerMain, String name);

    /**
     * This method is used to punish a member.
     *
     * @param beerMain    the beerMain
     * @param member      the member to punish
     * @param description the description of the rule
     * @param value       the value of the rule
     */
    BeerMain punishMember(BeerMain beerMain, String member, String description, int value);

    BeerMain removeRule(BeerMain beerMain, String ruleDescription);


    BeerMain deleteMember(BeerMain beerMain, String member);

    BeerMain payPunishment(BeerMain beerMain, String member, String description, int value);


}
