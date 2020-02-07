Word Counter Application
==============

Testing the application
--------------

The Word Counter application written with [Java SE 8] can be built using maven.

The unit test below verifies the functionality of the WordCounter class.

- WordCounterTest

It can be run using the following command.

    mvn test


Running the command below will fetch dependencies, build, compile the application and run the tests.

    mvn clean install


Note
--------------
The ITranslator interface is used to define the api of the translator class that will be provided. 

In the test, I have used Mockito to mock this interface.




