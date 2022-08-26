# ToDoList-app

This application is implemented as a taskslist-manager service.
The goal of the project is to enable users to create task lists and monitor their completion.
This project has an authentication system based on Spring security, using JWT token.
- --

### If you want to run this project on your computer, you need:
1. Clone this project:
2. Install Lombok plugin (or replace all @Data annotations with getters, setters and toString)
3. Install MySQL, configure application.properties file to make a connection to DB
4. Run 'TodolistAppApplication' class
5. Use Postman for sending your requests during testing this application
6. Injection of data are located in config/DataInitializer class.
- --

### The structure of project consists of 3 levels N-Tier architecture:
* Controller Layer.
* Service layer.
* Repository layer.
- --

### Available endpoints in the project:
- `http://localhost:8080/taskslists/` 
- `http://localhost:8080/tasks/` 
- `http://localhost:8080/users/`
- --

### Used technologies
- Java 11
- SpringBoot Data JPA
- MySql Database
- Lombok
- Swagger
- Maven checkstyle plugin
- Spring security
- JWT Token
