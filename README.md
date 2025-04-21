# Core Lending Factoring Microservice

## Overview

The Core Lending Factoring Microservice is a part of the Firefly platform that provides comprehensive factoring services for the lending domain. Factoring is a financial transaction and a type of debtor finance in which a business sells its accounts receivable (i.e., invoices) to a third party (called a factor) at a discount.

This microservice manages the entire lifecycle of factoring agreements, including debtors, invoices, advances, settlements, and fees. It is built using reactive programming principles to ensure high performance and scalability.

## Architecture

The project follows a modular architecture and is organized into the following modules:

### Modules

1. **core-lending-factoring-interfaces**
   - Contains DTOs (Data Transfer Objects) and service interfaces
   - Defines the contract for the microservice's API

2. **core-lending-factoring-models**
   - Contains entity definitions and database repositories
   - Manages database migrations using Flyway

3. **core-lending-factoring-core**
   - Contains service implementations
   - Implements business logic for factoring operations
   - Handles data mapping between entities and DTOs

4. **core-lending-factoring-web**
   - Contains REST controllers
   - Exposes the API endpoints
   - Handles HTTP requests and responses

## Key Features

- Management of factoring agreements
- Debtor management within factoring agreements
- Invoice processing and tracking
- Advance management for invoices
- Settlement tracking for invoices
- Fee calculation and management
- Reactive programming model using Spring WebFlux and R2DBC
- RESTful API with comprehensive documentation

## Technology Stack

- Java 21 with Virtual Threads
- Spring Boot
- Spring WebFlux for reactive programming
- R2DBC for reactive database access
- PostgreSQL database
- Flyway for database migrations
- OpenAPI/Swagger for API documentation
- Docker for containerization

## Setup and Installation

### Prerequisites

- JDK 21
- Maven
- PostgreSQL database
- Docker (optional, for containerized deployment)

### Environment Variables

The following environment variables need to be set:

- `DB_HOST`: PostgreSQL database host
- `DB_PORT`: PostgreSQL database port
- `DB_NAME`: PostgreSQL database name
- `DB_USERNAME`: PostgreSQL database username
- `DB_PASSWORD`: PostgreSQL database password
- `DB_SSL_MODE`: PostgreSQL SSL mode (e.g., "disable", "require")

### Building the Application

```bash
mvn clean package
```

### Running the Application

#### Using Maven

```bash
mvn spring-boot:run
```

#### Using Java

```bash
java -jar core-lending-factoring-web/target/core-lending-factoring-web.jar
```

#### Using Docker

```bash
# Build the Docker image
docker build -t core-lending-factoring .

# Run the Docker container
docker run -p 8080:8080 \
  -e DB_HOST=<your-db-host> \
  -e DB_PORT=<your-db-port> \
  -e DB_NAME=<your-db-name> \
  -e DB_USERNAME=<your-db-username> \
  -e DB_PASSWORD=<your-db-password> \
  -e DB_SSL_MODE=<your-db-ssl-mode> \
  core-lending-factoring
```

## Configuration

The application can be configured using Spring profiles:

- `dev`: Development environment with detailed logging
- `testing`: Testing environment with API documentation enabled
- `prod`: Production environment with minimal logging and API documentation disabled

Configuration is managed through the `application.yaml` file located in the `core-lending-factoring-web/src/main/resources` directory.

## API Documentation

The API documentation is available through Swagger UI when the application is running:

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## API Endpoints

The microservice exposes RESTful endpoints following this pattern:

- Factoring Agreements: `/api/v1/factoring-agreements`
- Debtors: `/api/v1/factoring-agreements/{factoringAgreementId}/debtors`
- Invoices: `/api/v1/factoring-agreements/{factoringAgreementId}/invoices`
- Advances: `/api/v1/factoring-agreements/{factoringAgreementId}/invoices/{factoringInvoiceId}/advances`
- Settlements: `/api/v1/factoring-agreements/{factoringAgreementId}/invoices/{factoringInvoiceId}/settlements`
- Fees: `/api/v1/factoring-agreements/{factoringAgreementId}/fees`

Each resource supports standard CRUD operations (GET, POST, PUT, DELETE) and filtering/pagination.

## Development Guidelines

### Code Style

This project follows standard Java code style conventions. Use the provided Maven configuration to ensure consistent formatting.

### Adding New Features

1. Define DTOs in the `core-lending-factoring-interfaces` module
2. Create entity classes in the `core-lending-factoring-models` module
3. Implement service interfaces in the `core-lending-factoring-core` module
4. Expose endpoints in the `core-lending-factoring-web` module

### Database Migrations

Database migrations are managed using Flyway. Add new migration scripts to the `core-lending-factoring-models/src/main/resources/db/migration` directory following the naming convention `V{version}__{description}.sql`.

## Testing

The project includes unit and integration tests. Run the tests using:

```bash
mvn test
```

## Monitoring

The application exposes the following actuator endpoints:

- Health: http://localhost:8080/actuator/health
- Info: http://localhost:8080/actuator/info
- Prometheus metrics: http://localhost:8080/actuator/prometheus

## Deployment

The application can be deployed as a standalone JAR or as a Docker container. For Kubernetes deployment, use the provided Dockerfile to build a container image.