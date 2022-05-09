**Credit Card Processing**

Code Structure

1. CreditCardSystem - Rest endpoints written in Java 8 using Springboot 2.6.7 framework.
2. CreditCardSystemUI - Angular 13 UI components for view and add credit cards. Also, used angular material and bootstrap.

A. Starting Tomcat for BE API
Download Java code and run using command line or any IDE - mvn clean install . The tomcat container would start on default port 8080.

API end points - 

List of existing cards - GET - http://localhost:8080/CreditCardSystem/api/v1/home
Add new Card details - POST - http://localhost:8080/CreditCardSystem/api/v1/add

B. Starting angular server
Download UI code and install packages using npm install.
Run angular server locally using ng serve on port 4200.
http://localhost:4200/

C. H2 in memory DB
login to H2 console using details provided in application.properties - http://localhost:8080/CreditCardSystem/h2

Write a small full stack application for a credit card provider. It should allow you to add new credit card accounts and view them as a list. 
The backend must be a RESTful API.

Requirements

Two REST Endpoints must be implemented
1.	"Add" will create a new credit card for a given name, card number, and limit
2.	Card numbers should be validated using Luhn 10
3.	New cards start with a £0 balance
4.	for cards not compatible with Luhn 10, return an error
5.	"Get all" returns all cards in the system

The endpoints should be given appropriate URLs and HTTP methods, according to RESTful design principles. There is no right and wrong answer here, but you may be asked to explain and justify your design, so give it some thought.

Validation

1.	All input and output will be JSON
2.	Credit card numbers may vary in length, up to 19 characters
3. Credit card numbers will always be numeric
