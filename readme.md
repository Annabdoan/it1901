
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2231/gr2231)

# Beer Punishment
This is the project in the subject ITP1901 by group 31. 

This repo contains a multi-package, multi-module javafx project for a beer punishment system. This application is primarily meant for people who work in teams or groups.
This app will provide a clear overview of what rules applies to the group/team, who has broken which rules and how many beers each individual has as punishment.
You can read more about the project here [Beer Punishment](/beer_punishment/README.md).

### Building and running the project
Note:
This project is built up with maven, and if maven is not installed you can use gitpod.

#### Runnning application with local access:

1. Navigate to beer_punishment directory.
2. Create JAR files for the project.
3. Deploy the packaged JAR file to the local repository. 
4. Compile source code.
4. Navigate to UI-module.
5. Launch the application.

>1. cd beer_punishment
>2. mvn package
>3. mvn clean install \
#Tests can be skipped by using "mvn clean install -DskipTests"
>4. mvn compile
>4. cd ui
>5. mvn javafx:run

### JaCoCo-report for test coverage

This project uses JaCoCo to measure and report test coverage. 
Each module will have its own report. The reports are located in the modules respective target directories. 

! This is to be done after instructions 1-4 in "Building and running the project" part. 

#### Getting the JaCoCo-reports 

1. Run the test.
2. Navigate to the target directory in each module.
3. Open the site folder.
4. Open the index.html file in your browser. 

>1. mvn verify
>2. mvn jacoco:report
>




### Organization of the code
* [beer_punishment](/beer_punishment)
    * [core](/beer_punishment/core)
      * [domain-logic](/beer_punishment/core/src/main/java/beerPunishment/core)
    * [json](/beer_punishment/json)
      * [json-logic](/beer_punishment/json/src/main/java/beerPunishment/json)
    * [ui](/beer_punishment/ui)
      * [ui-logic](/beer_punishment/ui/src/main/java/beerPunishment/ui)
      * [FXML](/beer_punishment/ui/src/main/resources)

[Read more about the modules and the code](/beer_punishment/README.md)


### The plan
The project is set to three releases.

###### Sprint 1
This sprint contains two user stories. 

User story 1: 
>A member of the group wants to keep track of the rules which leeds to punishment.

User story 2: 
>A group wants one persons late comings to be punished. \
Read more about it here: [Release 1](/docs/release1/release1.md)

###### Sprint 2
In this sprint we will continue to add more functionality. The goal is to implement most of the wanted logic. 

User story 3: 
>The group wants to display a list with the group members and how many penalties each member has.

Read more about it here: [Release 2](/docs/release2/release2.md)