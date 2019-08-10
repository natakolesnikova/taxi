# taxi

Requirement for the enviroment

1. MySql Server version at least 5.1
2. Spring Framefork
3. Java 1.8 
4. Maven
5. Your favoirite development tool

To run the project locally run commands
1. mvn clean all
2. mvn install
3. run DemoApplication class

There are two api
1. POST http://{server}:{port}/api/v1/driver - accept json object.
In successful case - return json driver object
In bad case - return json with not valid input fields that needs to be valid
2. PUT http://{server}:{port}/api/v1/driver{id} - accept id - id of driver and value for updating status true/false
In successful case - return json response like
{
    "ok": "Driver status is changed",
    "error": ""
}
In bad case - return json response like
{
    "ok": "",
    "error": "Driver with id 1345345 does not exist"
}

Link for testing api http://localhost:8080/swagger-ui.html (driver-controller)