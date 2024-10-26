# IEMS Guide - Getting Started

## Project Overview

The i-EMS Application Backend is a Spring Boot-based REST API designed to manage the functionalities of the i-EMS
system. The API provides essential operations such as managing users, handling requests, and providing data persistence
via JPA with MySQL. Swagger documentation is included for easier API exploration and testing.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/maven-plugin/build-image.html)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## Key features

### RESTful API:

- The API follows REST principles, allowing easy interaction through HTTP methods (GET, POST, PUT, DELETE) for managing
  resources.
- Endpoints are designed to be stateless and support JSON data exchange.

### Swagger documentation - http://localhost:8080/swagger-ui/index.html#/

- <b>springdoc-openapi</b> is integrated to automatically generate interactive API documentation, making it easy to
  explore and test the API through the Swagger UI.
- Dependencies: `springdoc-openapi-starter-webmvc-ui`.

### Jakarta validation:

- The project has migrated from javax validation to jakarta validation. This shift is aligned with the latest standards
  in enterprise Java, ensuring that data validation (e.g., @NotNull, @Email) complies with Jakarta EE specifications.
- Dependency: `jakarta.validation-api`.

### Data persistence with JPA:

- The API uses Spring Data JPA for interacting with the MySQL database. CRUD operations are abstracted and simplified
  using JPA repositories.
- Dependency: `spring-boot-starter-data-jpa`.

### Environmental Configuration:

- <b>spring-dotenv</b> is used for managing environment variables such as database connection details, making it easy to
  configure the application for different environments (e.g., development, production).
- Dependencies: `spring-dotenv`

### Unit and Integration Testing:

- The API includes tests using <b>Mockito</b> and <b>JUnit</b> for unit and integration testing to ensure that key
  functionalities are working as expected.
- Dependencies: `mockito-core`, `mockito-junit-jupiter`, and `spring-boot-starter-test`.

### Actuator for monitoring:

- <b>Spring Boot Actuator</b> is integrated to provide production-ready features like health checks and metrics to
  monitor the application's performance and availability.
- Dependency: `spring-boot-starter-actuator`.

## Folder Structure
### src/main/java/com/ipf/iems:

    controller/: Contains the controller (EmployeeController.java) which exposes REST APIs for the CRUD operations.
    entity/: Contains the Employee.java class, which represents the Employee entity and maps it to a database table.
    exception/: Contains custom exception classes (GlobalExceptionHandler.java and ResourceNotFoundException.java) for error handling.
    repository/: Contains the EmployeeRepository.java interface, which extends JpaRepository for database operations.
    service/: Contains the EmployeeService.java class, where business logic for handling employee data is implemented.
    IEmsApplicationBackendApplication.java: The main class that boots up the Spring Boot application.

### src/main/resources/:

    application.properties: The configuration file where you set up database credentials and other Spring Boot configurations.

### src/test/java/com/ipf/iems:

    Contains unit and integration test files (like EmployeeControllerTests.java) for testing the functionality of the application.

### pom.xml:

    The Maven configuration file where dependencies like Spring Boot, Spring Data JPA, and MySQL are defined.

### README.md:

    Its this document😁... A markdown file where I describe the project, how to set it up, and how to use it.

## Possible Future Enhancements

1. <b>Authentication Mechanisms</b><br />
   The API currently lacks authentication and authorization mechanisms. Implementing JWT-based authentication or OAuth2
   is planned to secure endpoints and restrict access based on user roles. <br /><br />
2. <b>Pagination Improvements</b><br/>
   Better pagination will ensure scalability when handling large datasets. <br /><br />
3. <b>Enhanced Test coverage</b><br/>
   Although unit tests and integration tests are included, more comprehensive test cases, particularly for edge cases
   and error scenarios, will be added to ensure robustness. <br /><br />

## Issues/Challenges

1. <b>Migration from javax to jakarta Validation:</b> One of the key challenges was migrating validation annotations
   from javax to jakarta, which required careful refactoring and testing to ensure that the new annotations behaved as
   expected. <br /><br />
2. <b>Dependency Management:</b> Ensuring the right dependencies and versions, especially with external libraries like
   spring-dotenv and springdoc-openapi. <br /><br />
3. <b>Migration from javax to jakarta Validation:</b> One of the key challenges was migrating validation annotations
   from javax to jakarta, which required careful refactoring and testing to ensure that the new annotations behaved as
   expected. <br /><br />