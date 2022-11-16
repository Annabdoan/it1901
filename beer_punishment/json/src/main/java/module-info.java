module beerPunishment.json {

    requires beerPunishment.core;
    requires com.google.gson;
    requires java.sql;
    //requires com.fasterxml.jackson.databind;
    //requires com.fasterxml.jackson.annotation;
    //requires com.fasterxml.jackson.core;
    //requires org.json;

    opens beerPunishment.json to com.google.gson;

    exports beerPunishment.json;
}