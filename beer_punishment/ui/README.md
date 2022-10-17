# UI
We want the user interface to be easy to use. 
The app is structured with a header displaying the application name, the main functionality on the right side and a list-view on the left side.

In order to build the user interface SceneBuilder was used to generate FXML-files.

To process user inputs and perform the wanted functionality we have structured the UI-module using the model view controller architecture. 


### The UI module consists of the following classes and files:

* BeerController &rarr; The link between UI and core & Json.
* BeerApp &rarr; Launches the UI.
* FXML &rarr; Creates the layout of our screen.
