# RestServer 

In [Release 3](./docs/release3/release3.md) the group were to 
set up a rest server. Therefore, a framework for implementation was needed.
The group decided quickly to listen to other students recommendations 
of using the *SpringBoot* framework.
Some of SpringBoots advantages that led to the groups decision were:

:round_pushpin: ***Faster development***


:round_pushpin: ***Its starters.*** 

SpringBoot offers starter dependencies we can implement in our Maven project.


:round_pushpin: ***Its learning curve.*** 

The framework is easy to understand, due to detailed examples in the documentation.

### Classes in the restserver module

:point_right: [BeerMainRestController](./src/main/java/beerPunishment/restserver/BeerMainRestController.java)

This controller handles the allowed HTTP-request methods.


:point_right: [BeerMainService](./src/main/java/beerPunishment/restserver/BeerMainService.java)

This class offers a method to create a BeerMain object, used in BeerMainRestController.

:point_right: [RestServerApp](./src/main/java/beerPunishment/restserver/RestServerApp.java)

This class contains the start method for the server application.


### Supported HTTP methods

#### Allowed methods

The projects allowed methods are

:round_pushpin: GET

:round_pushpin: PUT

:round_pushpin: POST

:round_pushpin: DELETE

#### Host

All requests are sent to corresponding endpoints. All HTTP methods have
the same endpoint;

> Host: localhost:8080/

The methods have different paths to be resolved to this endpoint, based on which action to be done. 

##### Content type

The requests also have the same content-type throughout all HTTP methods:

>Content-Type: application/json


###### Request get BeerMain object

> GET /beerMain

This request will give out a response with the BeerMain object as type JSON.

###### Request add a new member

> POST /members?name=examplemember

This request will add a new member, here with name *examplemember*.

###### Request delete an existing member

> DELETE /members?member=examplemember

This request will delete an existing member, with name *examplemember*.

###### Request adding a new rule

> POST /rules?description=ruledescription&value=numberofbeers

This request will add a new rule object, with a decription, *ruledescription*, and 
a value, *numberofbeers*.

###### Request deleting a rule

> DELETE /rules?rule=ruledescription

This request will delete an existing rule by using a rule description, *ruledescription*.

###### Request punishing a member

> PUT /punishMember?member=membername&description=ruledescription&value=numberofbeers

This request will punish a member, *membername* for breaking an existing rule with description *ruledescription*
and value *numberofbeers*.

###### Request paying up a punishment

> DELETE /payPunishment?member=membername&description=ruledescription&value=numberofbeers

This request will delete a punishment for a member *membername* breaking the rule *ruledescription* with value *numberofbeers*




