package beerPunishment.ui;

import beerPunishment.core.BeerMain;


public interface IBeerMainAccess {

    BeerMain getBeermain();

    /**
     * Make new rule.
     */
    BeerMain addRule(BeerMain beerMain, String description, int value);


    /**
     * Add member.
     */
    BeerMain addMember(BeerMain beerMain, String name);

    /**
     * Punish a member.
     */
    BeerMain punishMember(BeerMain beerMain, String member, String description, int value);

    BeerMain removeRule(BeerMain beerMain, String ruleDescription);


    BeerMain deleteMember(BeerMain beerMain, String member);

    BeerMain payPunishment(BeerMain beerMain, String member, String description, int value);


}
