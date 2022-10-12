# Json
The json module consist of classes that serialize and deserialize java-objects. 
The applications persistence is located in the json module.
We have created classes for converting Javacode and object-instances to json format and the other way around.
This had been done for all classes that need translation to and from json-format.

### The json module consists of the following serializers and deserializers:
* BeerMainDeserializer.java &rarr; Translates BeerMain from json to java-entities. 
* BeerMainSerializer.java &rarr; Translates BeerMain from java-entities to json.
* RuleDeserializer &rarr; Translates Rule from json to java-entities.
* RuleSerializer &rarr; Translates Rule from java-entities to json.


### Persistence:
* RulePersistence &rarr; Stores information needed to write and read to json file. 

### Module:
* RuleModule &rarr; Keeps track of serializers and deserializers
