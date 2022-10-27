module beerPunishment.restserver {
    requires spring.boot;
    requires spring.web;

    requires transitive beerPunishment.json;
    requires beerPunishment.core;

    opens beerPunishment.restserver to spring.web, spring.context;

    exports beerPunishment.restserver to spring.boot;



}