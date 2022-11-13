# Documentation for release 3 - gr2231

### :desktop_computer: Introduction 



For release 3 the group has produced the final 'shippable' product BeerPunishment.
During this release, the main focus for the group has been setting up an API.

The group discussed whether to continue with JavaFX or use CSS/JS to improve the user experience, but landed on continuing with JavaFX.
The decision was based on that none of the group members felt confident enough with using CSS/JS. Another factor to the decision
was the groups main focus revolving around team work, SCRUM and learning architecture more than learning a new technology and user experience. 
Due to the decision of continuing with JavaFX, the group has implemented new logic & functionality for
our application. See paragraph *New classes and modules for release 3* to have a look at the applications new grant. 

### :open_file_folder: Work habits and task management
After sprint 2 the group discussed the task management and the groups working habits as a part reflection. The group mainly had a discussion revolving
our large issues and branch use. The discussion concluded with an agreement that the group needed to
create smaller issues containing single problems, and not collect several problems in one issues.

Another thing concluded after this discussion was the need for a better use of the issue board.
In the two first sprints, the group forgot to check in on their issues, and mark them 
as 'done' when finished. Labels was also not implemented. A better task management was therefore concluded that the group needed. 

Another issue related problem the group discussed, was the tendency to start
solving a problem without creating an issue for the specific problem. The consequences of this was a loss of overview 
over problems needed to be done for the rest of the group. The group therefore felt that there were improvements
regarding issue management.

Regarding the use of branches, the group collectively figured out that before release 3 this needed to be improved.
In the previous sprints, the group found the use of branches difficult.
A conversation with the T.A was therefore taken, in order to improve the use of branch. Before sprint 3, branch names were not connected to their respective issue. 
The group learned before release 3 to create branches from an issue, in order to have a better overview, and 
navigation to an issue resolved in a branch. In this way, all group members, not just the person(s) working
on a branch, knows which issue it is thought resolving. The group stayed consistent in the use of branches throughout 
release 3.

With work habits, the group discussed the feedback from the T.A regarding sprint 2. Valuable feedback was received,
and used  to improve work habits prior to release 3. In this release, labels have been implemented for each issue. New labels
have been made, and issues now have several labels.

In addition, issues is now the base for all work, and is connected to merge requests and commit messages.
This is done by starting commit messages with (#number_of_issue). This is also done in branches, by (#number_of_issue)
being the start of the branch name. 
The group have also had a large focus on commit messages.
Both with connecting a message to an issue, but also focused on a good use of header, body and footers.
The headers have been short and describing, while the body has been longer with focus on explanations. 
The footer 'Co-authored-by' have mainly been used, due to the groups focus on pair-programming. 

On the other side, the groups general task distribution has remained the same as in the previous releases.
Pair-programming have been used most of the time, and issues have therefore been worked on by two or three, and assigned
to the person who felt most confident regarding the issue to be solved. 

### Quality ensurance of code
The group has continued to use *Jacoco* as a tool to make sure all classes are tested
to a minimum of 90 %.
In comparison to release 2, the group has had a larger focus on *Spotbugs*. In release 2, two *SpotBugs* were
still present in our code, however, the project now no longer contains *Spotbugs*.
The use of *CheckStyle* has mostly remained the same, with continuing the goal of 
having as few CheckStyle warnings as possible. The reason why the group does not have
a goal of zero CheckStyle warnings, is due to higher prioritizing some conventions.
The conventions prioritized for release 3 has been:
* **Our interface starts with capital I.**

CheckStyle hands out warnings due to the interface´s capital I.
The days before the project submission were used to go through the project, with intention 
of ensuring that criteria was met, and feedback from release 1 & 2 regarding code quality
were taken into consideration. In addition, these days were also used to go through method names and et cetera to ensure consistent use.
An example was the inconsistent use of delete/remove which was fixed. 

#### New classes and modules for release 3
As mentioned in the introduction, the group continued with JavaFX, and has therefore 
added in more functionality. Also, the implementation of a RESTful application meant new classes and a new module.

The new functionality is related to the new user stories. LINK TIL USER-STORIES.
The new functionality:
* :x: Being able to remove a member
* :x: Being able to remove a rule
* :yen: Members can pay up a punishment

In the setting up of the API, the group made a new module; 
* **Restserver**

Why we implemented a new module, is explained in LINK TIL README RESTSERVER

The new classes implemented:

#### Restserver
* BeerMainRestController.java
* BeerMainService.java
* RestServerApp.java

#### UI
* BeerMainLocalAccess.java
* BeerMainRemoteAccess.java
* IBeerMainAccess.java


#### User stories 
User stories for this release, are
all written in source README file.

:point_right: LINK TIL USER STORY 1


:point_right: LINK TIL 2


:point_right: LINK TIL 3

#### Further development
The group is satisfied with how the application turned out. 
The main purpose for this application has been being able to punish someone for breaking a
rule(s), and keeping an overview of who has which punishments. The application now
satisfies this, however, there are still functionality that can be added.

A user of BeerPunishment can´t be assigned to a group. If a user uses BeerPunishment to keep
control over rule breaches in several groups, the application does not offer a possibility
to separate different groups. If this were to be implemented, the group also would have needed to
create a log-in function. In that way, users could be added to a group based on a unique username. 
The group also has a wish to implement a record function in the future, 
such that a user can see which punishment he/she has gotten in the past and why.
In addition, the group would have called the API from a database, in order for it to be shareable. 

The two methods to be implemented first, due to them making the application
more complete:

* **A user should be able to create a group and assign group members.**
* **A user should be able to see his/hers previous punishment.**

However, the group did not have enough time to implement this functionality. 

#### Group and project reflection

This project has been a challenging project in different ways, and has lead to reflection of different aspects.
Some aspects have gone great, while others have room for improvements. This part of documentation is for reflection and learning
from our experience.

Not only has the project been time-consuming, it has been technical difficult, which have imprinted the team work.
None of the group members were confident with Maven, SpringBoot, API et cetera when beginning. In addition, SCRUM was
an untried work method for every group member. All these factors have made the learning curve steep. It has definitly been hard keeping
up with a good teamwork and communication when it was frustration over technical issues, however the group 
learned how to stay calm and communicate well over time.

##### Git-lab 
Git and git-lab was something the group members have used before, however none have had a major project in git-lab before.
Despite it being new, a good branch, issue & commit message practice was something
the group learned through trying and failing. In release 1 the group were not consistent with
the use of issues and commit messages. In this sprint, branches were also new, and 
not connected well enough to issues. 
In release 2, the group had a better understanding of how to use issues, and used the user stories as 
a start for all issues. However, the branch names and commit messages were still not linked to the issue numbers.

In release 3, the group started linking up branches to issues and its number. This made branches more
transparent, and they were easier to keep track of. In addition, this release was were the group
started linking up commit messages to the issue it was solving. The group consistently used 
feat/fix to indicate whether a commit was fixing or adding something. As mentioned in *Work habits and task management*, the group also
used header, body and footers better now. 

##### Group communication

The project started with the team setting up a team contract. The group contract had a base from the 
GRPI - model developed by David Kolb et al. 

:round_pushpin: ***Goals***

The goals discussed in the team contract were both individual and team-wise. 
The individuals of the group wrote down what they wanted to learn from this process. As a team
it was written down the goals of the project. 
The group focused on concrete goals, in order for the group to perform better.

:round_pushpin: ***Roles*** 

Different roles were discussed, however the group decided that all members should have the same role.
The group did not have a SCRUM master nor a customer, which made all members development team members. 
However, we discussed the groups weaknesses and strengths in order to have a better understanding of each other. 

:round_pushpin: ***Procedures***

This point revolves around securing the practical team work. In the contract we decided on a
fixed meeting time each week, however the group has reflected over this being over optimistic.
In reality, the group worked way over this fixed time each week, and this is a point for improvement.


:round_pushpin: ***Interpersonal relationships***

The interpersonal relationships rely on the three factors over. This was a point the group had discussed in the team contract.
However, the group experienced that in real life, not everything works the way the contract says they should.
An example was when two group members disagreed on implementation of the API.
The conflict was not solved liked the contract said it should, with being calm and listening to each other.
This was something the group discussed, and collected thoughts around how to improve if it were to occur on a later occasion. 

##### Task management 

Most of the reflection regarding task management is under the point *Work habits and task management.* 
However, by the end of release 3 the group reflected around 
task management for writing tests. Tests were assigned to the members who had spare time, and not 
necessarily to the members who wrote the code tested. By assigning test to the person writing the
code, time could have been saved. 

Another thing the group discussed was the saving of CheckStyle warnings until the very last.
The group agrees that this is something that should have been done along the way. Simple fixes 
like writing a Java-Doc comment to a method, is something the group should have done 
right after writing that method, not saved for the days before submission. These two points is something the
group has learned from. 


#### Documentation 
It was mandatory to have a README - file to each release. The group wrote these 
as the last thing each release, and had good use of them. The group used the README - files to sum up and collect an overview of the project.
However, seeing it from retrospect, the group felt that a more constructive take should have been done.
Therefore, this is tried done with this release documentation for release 3.

#### Conclution

To conclude this experience, the group has learned new technologies and the
importance of communicating. Also, SCRUM has been introduced, and tried out. 
Architecture of a large project has been a major learning experience which the group will
take with in. The project turned out to be a positive experience, even with some bumps in the roads, 
and BeerPunishment works the way we intended it to. 







