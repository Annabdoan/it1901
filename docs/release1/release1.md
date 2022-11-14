# Documentation for release 1 - gr2231

### Introduction
For release 1 we started by discussing what kind of app we wanted to make, and chose to make something we all found usefull 
in our lives. The application is called "Beer Punishment" and the motivation and purpose is described in the
[source-readme](../../beer_punishment/README.md). We have built the foundation of the project with two domain logic classes, a filehandler,
a controller-class and an app-class. The project has its code repository on git.

### Working habits and task management:
We used issues to to divide the submission into issues, and assigned them to the various group members. 
Gradually we understood that it was better to work in pairs, and that this was more efficient than sitting alone.
It was somewhat difficult to know in which order we should solve the issues. It took a lot of time and work to 
understand the pom-file and how to add the maven-dependency.

### For release one the project consists of the following classes and resources:

#### Beer_punishment
* BeerApp.java
* BeerController.java
* BeerMain.java
* FileHandler.java
* Rule.java

#### At release 1 the app will consist of this scene
![alt_text](uirelease1.png)


### Intended functionality for release 1:
* We have created a beer punishment system that will keep an overview of rules and corresponding penalties for breaking them.
* We have implemented functionality to be able to read from a file. In our case, the file is  an overview of which rules apply and corresponding penalties for breaking them.
* We have implemented the logic to be able to write and read new rules to a file, but have not implemented the controller logic for this.




