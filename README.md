# IEMS Guide - Getting Started

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

### HELP.md:

    Its this document😁... A markdown file where you describe the project, how to set it up, and how to use it.

[//]: # (TODO: ADD DEPENDENCIES)
