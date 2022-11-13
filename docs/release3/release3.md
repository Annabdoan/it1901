# Documentation for release 2 - gr2231

### Introduction 

:desktop_computer: 

For release 3 the group has produced the final 'shippable' product BeerPunishment.
During this release, the main focus for the group has been setting up an API.

The group discussed whether to continue with JavaFX or use CSS/JS, but landed on continuing with JavaFX.
The decision was based on the fact that none of the group members felt confident enough with using CSS/JS, and
because the groups focus mainly has revolved around team work and learning architecture more than learning to make
a good UI.
Due to the decision of continuing with JavaFX, the group has implemented new logic & functionality for
our application. See point 'New classes and modules for release 3' to see the new implementations.

The group

### Work habits and task management
After sprint 2 the group discussed task management. The group mainly had a discussion revolving
our large issues and branch use. The discussion concluded with an agreement that we needed to
create smaller issues containing single problems, and not collect several problems in one issues.
Another thing concluded after this discussion was that a better use of the issue board was needed.
In the two first sprints, 1 & 2, the members of the group forgot to check in on their issues, and mark them 
as 'done' when finished. A better task management was therefor concluded that the group needed. 

Another issue related problem the group discussed, was that the members of the group had a tendency to start
on a problem without creating an issue. The consequences of this was that the person who forgot to make an issue lost some overview 
of problems needed to be done, and the rest of the group were not able to follow through the issue board what had been done, and which problems that
had occurred. The group therefore felt that there were improvements regarding good git practice.

Regarding the use of branches, the group collectively figured out that before release 3 this needed to be improved.
In the previous sprints, the group found branch use hard, and something that needed to be worked on.
Therefore, a conversation with the T.A was needed. Before sprint 3, branch names were not connected to their respective issue. 
In sprint3, the group has created branches from an issue, in order to have a better overview, and 
because it is easier to navigate to the issue being resolved in a branch. In this way, all group members, not just the person(s) working
on a branch, knows which issue it is thought resolving.

With work habits, the group discussed the feedback from the T.A regarding sprint 2. We received valuable feedback,
and used it to improve the work habits to sprint3. In sprint 3, labels have been used better for each issue. New labels
have been made, and issues now have several labels. The group have consistently used code review to improve code quality.
In addition, issues is now the base for all work, and is connected to merge requests and commit messages.
This is done by starting commit messages with (#number_of_issue). This is also done in branches, by (#number_of_issue)
being the start of the branch name. The group has also had a large focus on commit messages.
Both with connecting a message to an issue, but also focused on a good use of header, body and footers.
The headers have been short and describing, while the body has been longer with focus on explanations. 
The footer 'Co-authored-by' have mainly been used, due to the groups focus on pair-programming. 

On the other side, the groups general task distribution has remained the same as in the previous sprints.
Pair-programming has mostly been used, and has therefore for the most assigned issues to groups of two or three.
The group members assigned to an issue, has been the member who felt most confident in solving the issue. When pair-programming, we 
have added 'Co-authored-by' as a footer to the commit message. 

### Quality ensurance of code
The group has continued to use 'Jacoco' as a tool to make sure all classes are tested
to a minimum of 90 %.
In comparison to release 2, the group has had a larger focus on 'Spotbugs'. In release 2, two SpotBugs were
still present in our code, however, now the project no longer contains Spotbugs.
The use of CheckStyle has mostly remained the same, with continuing the goal of 
having as few CheckStyle warnings as possible. The reason why the group does not have
a goal of zero CheckStyle warnings, is due to us higher prioritizing some conventions.
The conventions for release 3 has been:
* Our interface starts with capital I.

CheckStyle gives out warnings due to the interface´s capital I. However the group decided to ignore this warning.
The days before the project submission was used to go through the whole project, with intention 
of ensuring that criteria was met and the feedback from release 1 & 2 regarding code quality
were gone. In addition, these days was also used to go through method names and et cetera to ensure consistent use.
An example was the inconsistent use of delete/remove. 

#### New classes and modules for release 3
As mentioned in the introduction, the group continued with JavaFX, and has therefore 
added in more functionality. Also, the implementation of a RESTful application meant new classes and a new module.

The new functionality is related to the new user stories. LINK TIL USER-STORIES.
The new functionality:
* Being able to remove a member
* Being able to remove a rule
* Members can pay up a punishment

In the setting up of the API, the group made a new module; 
* restserver

Why we implemented a new module, is explained in LINK TIL README RESTSERVER

The new classes is:

#### restserver
* BeerMainRestController.java
* BeerMainService.java
* RestServerApp.java

#### UI
* BeerMainLocalAccess.java
* BeerMainRemoteAccess.java
* IBeerMainAccess.java










