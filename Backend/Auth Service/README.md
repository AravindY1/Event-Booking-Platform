# Auth Service (Spring Boot)

Handles user registration, login, JWT generation, and user retrieval.

##  Tech Stack
- Java 17
- Spring Boot
- Spring Security + JWT
- Spring Web
- Spring Data JPA
- postgreSQL
- REST APIs

##  Running the Service

cd auth-service
mvn clean install
mvn spring-boot:run


Service runs at:
 http://localhost:8080

application.properties

spring.application.name=project 

spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.username=postgres
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect


Database Tables

Users table

user_id        BIGINT   PK
full_name      VARCHAR
email          VARCHAR (unique)
password       VARCHAR (encrypted)
role           VARCHAR (admin/customer)


API Endpoints
POST /auth/register
Registers a new user and sends email.
Request


{
  "fullName": "Jack Reacher",
  "email": "jack@example.com",
  "password": "test123",
  "role": "customer"
}


POST /auth/login
Returns JWT + user info.
Response

{
  "message": "user login successful",
  "token": "eyJhbGciOiJI...",
  "email": "jack@example.com",
  "fullName": "Jack Reacher",
  "role": "customer"
}


GET /auth/allUsers (ADMIN only)
Returns a list of all users.

Communication
Auth service is used by:
	•	Booking Service (needs email from JWT)
	•	Frontend (login/register)