# GPCDemo (GPCPaymentParcer) (backend)

This GPC application is a web-based platform that allows users to get payment data from json

## Technologies Used

- Java: The backend of the application is built using Java programming language.
- Spring Framework: The application utilizes the Spring Framework, including Spring Boot, Spring MVC, and Spring Data
  JPA.
- Swagger: Swagger is used for API documentation and specification.
- ModelMapper: ModelMapper is used for object mapping between DTOs (Data Transfer Objects) and entities.
- PostgreSQL: The application uses PostgreSQL as the relational database management system.
- Git: Git is used for version control and collaborative development.

## Getting Started

To run the this app locally, follow these steps:

1. Clone the repository:

```
https://github.com/aleksandr-poliakov/GPCdemoApp.git
```

2. Configure the database:
    - Install PostgreSQL and create a new database.
    - Update the database configuration in the `application.properties` file with your database credentials.

3. Build and run the application:
    - Navigate to the project directory and run the following command to build the application:

   ```
   ./mvnw clean install
   ```

    - Run the application using the following command:

   ```
   ./mvnw spring-boot:run
   ```

4. Access the application:
    - Once the application is running, open a web browser and go to `http://localhost:8080` to access the application.

<Br>
Or run the GPC app locally with docker:

1. Clone the repository:

```
https://github.com/aleksandr-poliakov/GPCdemoApp.git
```


## API Documentation

The Real Estate Application provides API documentation using Swagger. To view the API documentation, follow these steps:

1. Run the application locally.

2. Open a web browser and go to `http://localhost:8080/swagger-ui/index.html#/`.

3. The Swagger UI page will display the available API endpoints along with detailed documentation and examples.




