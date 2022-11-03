module beerPunishment.restserver {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.webmvc;
    requires spring.beans;
    requires spring.data.commons;



    requires transitive beerPunishment.json;
    requires beerPunishment.core;


    opens beerPunishment.restserver to spring.web, spring.context, spring.core;

    exports beerPunishment.restserver to spring.boot;



}