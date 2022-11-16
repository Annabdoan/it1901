module beerPunishment.restserver {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.webmvc;
    requires spring.beans;
    requires spring.data.commons;
    requires java.desktop;



    requires transitive beerPunishment.json;
    requires beerPunishment.core;
    requires spring.context;
    requires com.google.gson;


    opens beerPunishment.restserver to spring.web, spring.context, spring.core, spring.beans;

    exports beerPunishment.restserver to spring.boot;



}