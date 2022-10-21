module beerPunishment.json {

    requires beerPunishment.core;
    requires gson;
    requires java.sql;
    //requires com.fasterxml.jackson.databind;
    //requires com.fasterxml.jackson.annotation;
    //requires com.fasterxml.jackson.core;
    //requires org.json;

    opens beerPunishment.json to gson;

    exports beerPunishment.json;
}