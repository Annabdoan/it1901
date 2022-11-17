
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2231/gr2231)

# :beer: Beer Punishment :beer: 

This is the project in the subject ITP1901 by group 31. 

This repo contains a multi-package, multimodule javafx project for a beer punishment system. This application is primarily meant for people who work in teams or groups.
The application will provide a clear overview of which rules apply, who has broken which rules and how many beers each individual has as punishment.
Functionality revolving adding a new rule, member and punishing a member is provided. 
The application also offers the possibility to delete rules, members and to pay up for punishments. 


### Building and running the project:
Note:

This project is built up with maven. If maven is not installed, GitPod can be used. Click the 
GitPod icon on top, and you are ready to run the project.



#### Running application with local access:

1. Navigate to beer_punishment directory.
2. Create JAR and WAR files for the project.
3. Deploy the packaged JAR file to the local repository.
4. Navigate to UI-module.
5. Run the application.

>1. cd beer_punishment
>2. mvn package
>3. mvn clean install \
  #If application has been run with remote access prior to remote, this step can be skipped.
  #Tests can be skipped by using "mvn clean install -DskipTests"
>4. cd ui
>5. mvn javafx:run

#### Running application with remote access:

1. Navigate to beer_punishment directory.
2. Create JAR and WAR files for the project.
3. Deploy the packaged JAR file to the local repository.
4. Navigate to the restserver directory.
5. Start server (this needs to be done in a new terminal).
6. Navigate to the UI folder
7. Run the application 

>1. cd beer_punishment
>2. mvn package
>3. mvn clean install \
    #If application has been run with local access prior to remote, this step can be skipped.
    #Tests can be skipped by using "mvn clean install -DskipTests"
>4. cd restserver
>5. mvn spring:boot-run
>6. cd ui
>7. mvn javafx:run


### :postbox:  Generate shippable product


This product can be conducted into a shippable product with the help of ***Jlink*** and ***Jpackage***.
Jlink and Jpackage makes it possible to run the project on local machines desktop, from launching from the application folder. 

#### Package project to a desktop application

NOTE 

:round_pushpin: Point three is only needed if this is done using GitPod.

:round_pushpin: The application can only be made to the operating system that the user is on.
GitPod is run using Linux. 

1. Navigate to the UI module (if not already there).
2. Run Jlink 
3. Install Fakeroot
4. Run Jpackage
5. Navigate to target folder.
6. Navigate to dist folder.
7. Open the file in dist folder. 

> 1. cd UI 
> 2. mvn javafx:jlink
> 3. sudo apt-get update
> 3. sudo apt-get install fakeroot
> 4. mvn jpackage:jpackage


### JaCoCo-report for test coverage

This project uses JaCoCo to measure and report test coverage. More about how and why the group used Jacoco is described
in [Release 3](./docs/release3/release3.md).

Each module will have its own report. The reports are located in the modules respective target directories. 

! This is to be done after instructions 1-4 in *Building and running the project*. 

#### Access JaCoCo-reports / Tests

1. Delete JSON-file
2. Run the test.
3. Create Jacoco report. 
4. Navigate to the target directory in each module.
5. Open the site folder.
6. Open the index.html file in your browser. 

>1. rm -r ~/beerPunishmentRemote.json
>2. mvn verify
>3. mvn jacoco:report
>4. cd *module*/target






### Organization of the code
* [beer_punishment](./beer_punishment)
    * [core](./beer_punishment/core)
      * [domain-logic](./beer_punishment/core/src/main/java/beerPunishment/core)
    * [json](./beer_punishment/json)
      * [json-logic](./beer_punishment/json/src/main/java/beerPunishment/json)
    * [restserver](./beer_punishment/restserver)
      * [restserver-logic](./beer_punishment/restserver/src/main/java/beerPunishment/restserver)
    * [ui](./beer_punishment/ui)
      * [ui-logic](./beer_punishment/ui/src/main/java/beerPunishment/ui)
      * [FXML](./beer_punishment/ui/src/main/resources)

[Read more about the modules and the code](./beer_punishment/README.md)


### Overall plan
The project is set to three releases. Each release represents a sprint.


##### Release 1
This is the first release for this project. 

Read more about it here: [Release 1](./docs/release1/release1.md)

##### Release 2
In this release the group will continue to add more functionality. The goal is to implement most of the wanted logic.

Read more about it here: [Release 2](./docs/release2/release2.md)


##### Release 3
This is the final release, where all the intended logic is to be implemented. 

Read more about it here: [Release 3](./docs/release3/release3.md)
