# Assignment One

## Description
RESTFul API used to allow external developers to request an API key for an organiation's public API

## Assumptions
Built with Spring Boot. 
Data is stored in a simple static map of users and their applications. 
Model validation done with Hibernate validator (JSR 303)

## Building
Dependencies via Maven. a simple mvn package will build the jar, which can then be executed with java -jar

## API Examples
### Add a user
`curl http://localhost:8080/api/users -X "POST" -H "Content-Type: application/json" -d "{\"firstName\":\"justin\",\"lastName\":\"miller\",\"email\":\"someemail@somecompany.com\",\"telephone\":\"8015551212\"}"`
### Get a user
`curl http://localhost:8080/api/users/someemail@somecompany.com`
### Update a user
`curl http://localhost:8080/api/users/someemail@somecompany.com -X "PUT" -H "Content-Type: application/json" -d "{\"firstName\":\"justin\",\"lastName\":\"miller\",\"email\":\"someemail@somecompany.com\",\"telephone\":\"8005551212\"}"`
### Delete a user
`curl http://localhost:8080/api/users/someemail@somecompany.com -X "DELETE"`
### Add a user application
`curl http://localhost:8080/api/users/someemail@somecompany.com/applications -X "POST" -H "Content-Type: application/json" -d "{\"name\":\"app name\",\"description\":\"some app description\"}"`
### Get a users applications
`curl http://localhost:8080/api/users/someemail@somecompany.com/applications`



