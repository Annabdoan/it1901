module beerPunishment.json {

    requires beerPunishment.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires org.json;

    exports beerPunishment.json;
}