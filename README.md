# Stock Trading Application

This project is a Stock Trading Application implemented using Spring Boot framework for backend development. It provides CRUD (Create, Read, Update, Delete) operations for managing stock trades and orders. 

## Features

- **CRUD Operations**: Allows users to perform CRUD operations on stock trades and orders.
- **Trade Details Management**: Manages trade details including trade date/time, stock name, listing price, quantity, type (buy or sell), and price per unit.
- **Order Management**: Manages orders with status tracking (e.g., created, confirmed, etc.).
- **RESTful API**: Exposes RESTful endpoints for interacting with the application.
- **Persistence**: Uses relational database (such as MySQL, PostgreSQL) for data storage.
- **Security**: Implements basic security measures to protect sensitive endpoints.

## Technologies Used

- **Spring Boot**: For building the backend application.
- **Spring Data JPA**: For data access and persistence.
- **Hibernate**: As the ORM (Object-Relational Mapping) framework.
- **MySQL**: As the relational database.
- **Maven**: For project management and dependency resolution.
- **RESTful API**: For communication with clients.

## Project Structure

- `src/main/java`: Contains the Java source code.
  - `com.example.trading`: Root package.
    - `controller`: Contains REST controller classes.
    - `model`: Contains entity classes (TradeDetails, OrderMaster).
    - `repository`: Contains Spring Data JPA repository interfaces.
    - `service`: Contains service classes to handle business logic.
- `src/main/resources`: Contains application properties and configurations.
- `src/test/java`: Contains test classes.

## Getting Started

To run this application locally, follow these steps:

1. Clone this repository.
2. Configure the database settings in `application.properties`.
3. Build the project using Maven/Gradle.
4. Run the application.
5. Access the endpoints using a REST client (such as Postman).

