
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2231/gr2231)

# :beer: Beer Punishment :beer: 

This is the project in the subject ITP1901 by group 31. 

This repo contains a multi-package, multimodule javafx project for a beer punishment system. This application is primarily meant for people who work in teams or groups.
The application will provide a clear overview of which rules apply, who has broken which rules and how many beers each individual has as punishment.
Functionality revolving adding a new rule, member and punishing a member is provided. 
The application also offers the possibility to delete rules, members and to pay up for punishments. 


### Building and running the project
Note:
This project is built up with maven, and if maven is not installed GitPod can be used. Click the 
GitPod icon and you are ready to run the project.

#### Running application with local access:

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

! This is to be done after instructions 1-4 in *Building and running the project*. 

#### Access JaCoCo-reports 

1. Run the test.
2. Navigate to the target directory in each module.
3. Open the site folder.
4. Open the index.html file in your browser. 

>1. mvn verify
>2. cd target
>3. mvn jacoco:report
>




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
The project is set to three releases. Each release represents a sprints.


##### Release 1
This release contains two user stories:

*User story 1:* 
>A member of the group wants to keep track of the rules which leeds to punishment.

*User story 2:*
>A group wants one person´s late comings to be punished. 
> 
Read more about it here: [Release 1](./docs/release1/release1.md)

##### Release 2
In this release the group will continue to add more functionality. The goal is to implement most of the wanted logic. 

*User story 3:*
>The group wants to display a list with the group members and how many penalties each member has.

Read more about it here: [Release 2](./docs/release2/release2.md)


##### Release 3
This is the final release, where all the intended logic is to be implemented. 

*User story 4:*
> Anna wants to punish Sara for one of her several rule breaks. She unfortunately gets Sara´s name entered incorrectly in the application, and wants to remove this new member from the app. She therefore wants to create a function to delete this name, 
so that only desired members are displayed on the application.

*User story 5:*
> The group has decided that phone use no longer should be punished, as it has proven to not be particularly problematic. They therefore want to have the option to remove existing rules in the application, 
so that they can delete the rule of no phone use.

*User story 6:*
> Over the course of several months, Maurice has been punished with various beer penalties, and is starting to get loads of beers he has to pay for. To make sure that the amount does not get too large towards the end of the year, he wants to pay down his debt. Maurice wants to delete 
his existing rule breaks in the app after paying them up. 