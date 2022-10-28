module beerPunishment.restserver {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.webmvc;
    requires spring.beans;


    requires transitive beerPunishment.json;
    requires beerPunishment.core;


    opens beerPunishment.restserver to spring.web, spring.context;

    exports beerPunishment.restserver to spring.boot;



}