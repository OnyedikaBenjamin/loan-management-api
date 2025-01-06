# Loan Management System

## Overview
The Loan Management System is a Spring Boot application that provides functionalities for managing loans, including loan applications, status updates, interest calculations, and audit logging for critical actions. This document outlines setup instructions and explains the design decisions and trade-offs made during the implementation.

---

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 17 or later
- Maven (for building and managing dependencies)
- PostgreSQL or MySQL (as the database)
- An IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code)
- Git (for version control)

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/OnyedikaBenjamin/loan-management-api
   cd loan-management-system
   ```

2. **Configure the Database**
   - Create a new database in PostgreSQL or MySQL.
   - Update the `application.yml` file with your database credentials:
     ```yaml
     spring:
       datasource:
         url: jdbc:postgresql://localhost:5432/loan_management
         username: your_username
         password: your_password
       jpa:
         hibernate:
           ddl-auto: update
         show-sql: true
     ```

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   The application will start on `http://localhost:8080` by default.

5. **Access the Endpoints**
   - Use Postman or any REST client to interact with the API endpoints.
   - Import the provided Postman collection for testing.

---

## Design Decisions and Trade-offs

### Design Decisions

#### 1. **Layered Architecture**
- **Reason**: The application is structured into layers (Controller, Service, Repository) to separate concerns and improve maintainability.
- **Benefits**:
  - Simplifies testing by isolating business logic.
  - Enhances scalability and readability.

#### 2. **Audit Logging**
- **Reason**: Logging critical actions (e.g., loan application, status updates) ensures traceability and accountability.
- **Implementation**:
  - A dedicated `AuditLogService` records key actions.
  - Logs are stored in a database for easy querying.

#### 3. **Validation**
- **Reason**: Input validation is critical to maintain data integrity.
- **Implementation**:
  - Used annotations like `@NotNull`, `@Min`, and custom validators for loan-related fields.

#### 4. **Error Handling**
- **Reason**: Provide meaningful feedback to API consumers.
- **Implementation**:
  - Centralized exception handling using `@ControllerAdvice`.
  - Custom exceptions for domain-specific errors.

#### 5. **Database Choice**
- **Reason**: MySql was chosen for its robust features and open-source nature.
- **Trade-offs**:
  - While PostgreSQL is highly reliable, it might introduce a learning curve for developers unfamiliar with it.

#### 6. **Spring Boot**
- **Reason**: Chosen for its simplicity and extensive ecosystem.
- **Benefits**:
  - Reduces boilerplate code.
  - Integrates well with modern tools and libraries.

---

### Trade-offs

#### 1. **Synchronous API Calls**
- **Decision**: APIs are designed to handle requests synchronously.
- **Trade-off**:
  - Simplifies implementation but might lead to delays under heavy load.
  - Future enhancement: Implement asynchronous processing using Kafka or RabbitMQ.

#### 2. **Logging Mechanism**
- **Decision**: Logs are stored in a relational database.
- **Trade-off**:
  - Querying logs from the database is convenient, but it might impact performance during high write loads.
  - Future enhancement: Use a dedicated logging system like ELK or Splunk.

#### 3. **Monolithic Architecture**
- **Decision**: The application is monolithic.
- **Trade-off**:
  - Easier to develop and deploy initially but less scalable compared to microservices.
  - Future enhancement: Refactor into microservices for better scalability.

---

## Future Enhancements
- Implement role-based access control for more secure APIs.
- Add a dedicated notification service for loan approvals or rejections.
- Integrate with external systems for credit score checks.
- Provide a UI dashboard for administrators.

---

For further assistance, please contact [your-email@example.com].

