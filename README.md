# Recipe App - CGI Assessment

## Overview

This is a Spring Boot-based Recipe Application created as part of a coding assessment.  
The app exposes REST endpoints to:
- Retrieve all recipes
- Search recipes by ingredients

Recipes data is loaded from a JSON file (`recipes.json`) in the resources folder.

---

## Features

- **GET /recipes**: Returns all stored recipes sorted by name.
- **POST /recipes/search**: Accepts an array of ingredients and returns recipes that contain *all* those ingredients. Input validation included.
- Minimal frontend to interact with the API.
- Integrated Swagger UI for API documentation and testing.
- Unit tests covering service and controller logic.

---

## Requirements

- Java 17+
- Maven 3.6+
- Internet connection (for Maven dependency download)

---

## Setup and Run

1. Clone the repository:
    ```bash
    git clone <repo-url>
    cd <project-folder>
    ```
2. Build the project:
    ```bash
    mvn clean install
    ```
3. Run the application:
    ```bash
    mvn spring-boot:run
    ```
4. Open your browser and access the app:
    - Frontend UI: [http://localhost:8080/index.html](http://localhost:8080/index.html)
    - Swagger UI API docs: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Project Structure

- `src/main/java` - Java application code
- `src/main/resources` - Static assets and `recipes.json` data file
- `src/test/java` - Unit tests
- `pom.xml` - Maven build configuration

---

## Notes

- The application loads recipes from the `recipes.json` file at startup.
- Tests use injected data to avoid dependencies on JSON during testing.
- Swagger UI provides easy exploration and testing of REST APIs.



