# UI
The groups main focus was not design, and making the application aesthetic. 
However, the group has strived for a UI that follows Don Norman´s designs principles.
Two examples of principles with how the group has secured them being:

:round_pushpin: Affordance

For this project, the affordance is good. The buttons represent good affordance because they all
have a clear frame and dimension. 

:round_pushpin: Constraints

The input fields all have a grey example text that shows what the UI supports. 


The application is structured with a header with the applications name, the main functionality on the right side and a view-list on the left side.

In order to build the user interface the group has used JavaFX to generate a FXML file for our scene. 
Because this application only is structured with one scene, the group decided that one controller where enough. 
If the project where to incorporate several scene, one controller per scene would have been the standard.



## The UI module consists of the following classes and Interface:
:point_right: [BeerApp](./src/main/java/beerPunishment/ui/BeerApp.java)
Application class, launches the application.

:point_right: [BeerController](./src/main/java/beerPunishment/ui/BeerController.java)

Binds together Core logic and the FXML file. The group has used the MVC principle, with the model being the Core logic.

:point_right: [BeerMainLocalAccess](./src/main/java/beerPunishment/ui/BeerMainLocalAccess.java)

Class that centralizes access to a BeerMain object.

:point_right: [BeerMainRemoteAccess](./src/main/java/beerPunishment/ui/BeerMainRemoteAccess.java)

lass that centralizes access to a BeerMain object. 

:point_right: [IBeerMainAccess](./src/main/java/beerPunishment/ui/IBeerMainAccess.java)

Works as a binder for BeerMainLocalAccess and BeerMainRemoteAccess. 