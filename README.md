###Getting Started

####Reference Documentation

Solution to the "Secondary current accounts" assignment. Note that I could not get clarity on "Secondary current accounts" from Blue Harvest so I am assuming them to be normal "current accounts". Some ideas which I had thought of initially are intentionally not implemented because of scope of problem statement and time-lines viz. extra API, Generalizations i.e. Types of account since there is only one type, command design pattern for transaction verbs like DEBIT, CREDIT, INTEREST etc.

However emphasis is given on:
*	code quality and readability
*	good REST API design (as in Richardson maturity model)
*	adherence to good use of general industry standard tools
*	Technologies used are Spring Boot, Spring Security, Spring Data, Gradle, H2, JUnit, Mockito, HTML, JQuery

####How to run
*	App can be run as a standalone Spring Boot app from IDE (Preferably **STS** as all the indentations, formatting, IDE plugin are specific to this IDE on Windows). Execute below command if project structure is broken.

	gradlew cleanEclipse eclipse   

OR

*	Alternatively, app WAR can be deployed on Tomcat. Copy the WAR into Webapps folder.
  
	gradlew clean build -x test
	

####Run unit tests

	gradlew test

####How to use UI or API

Navigate to <http://localhost:8080/assignment-1.0/>. If asked, credentials are **system/system**.

In order to use API, you will need Postman chrome app [download here](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en). Working postman collection "BlueHarvestAssignment.postman_collection.json" is included in the root of the project which contains ready to hit APIs. Import it into your postman chrome app. API is secured using Spring Security basic authentication (**system/system**). Two users with ID 1 and 2 are created on application startup.

Sample REST endpoints are-  
GET <http://localhost:8080/assignment-1.0/customers/1> [This endpoint displays few additional fields apart from what were asked in problem statement so that information can be used in below 2 endpoints]

POST <http://localhost:8080/assignment-1.0/accounts>

POST <http://localhost:8080/assignment-1.0/transact> 


