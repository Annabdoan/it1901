module beerPunishment.json {

    requires beerPunishment.core;
    requires com.google.gson;
    requires java.sql;

    opens beerPunishment.json to com.google.gson;

    exports beerPunishment.json;
}
