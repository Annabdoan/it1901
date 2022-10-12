
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2231/gr2231)


Dette prosjektet er besvarelsen til gruppe 31 i faget ITP1901.

# Beer Punishment
This repo contains a multi-package, multi-module javafx project for a beer punishment system. This application is primarily meant for people who work in teams or groups.
This app will provide a clear overview of what rules applies to the group/team, who has broken which rules and how many beers each individual has as punishment.
You can read more about the project here [Beer Punishment](/beer_punishment/README.md).

### Building and running the project
Note:
This project is built up with maven, and if maven is not installed you can use gitpod.

#### Runnning application with local access:
1. Navigate to beer_punishment directory
2. Install dependencies and run test
3. Navigate to ui directory
4. mvn javafx:run



### Organization of the code
* [beer_punishment](/beer_punishment)
    * [core](/beer_punishment/core)
      * [domain-logic](/beer_punishment/core/src/main/java/beerPunishment/core)
    * [json](/beer_punishment/json)
      * [json-logic](/beer_punishment/json/src/main/java/beerPunishment/json)
    * [ui](/beer_punishment/ui)
      * [javafx](/beer_punishment/ui/src/main/java/beerPunishment/ui)
      * [javafx](/beer_punishment/ui/src/main/resources)

[Read more about the modules and the code](/beer_punishment/README.md)


### The plan
The project is set to three releases.

###### Sprint 1
This sprint contains two user stories.
User story 1:
TODO KORT RECAP

User story 2:
TODO KORT RECAP
Read more about it here: [Release 1](/docs/release1.md)

###### Sprint 2
In this sprint we will continue to add more functionality. 
TODO user stories to what we have implemented.
Read more about it here: [Release 2](/docs/release2.md)

