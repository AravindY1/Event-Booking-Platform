

Processes payments for bookings.  
Mock logic: **even amounts = SUCCESS**, odd amounts = FAILED**.

## Tech Stack
- Spring Boot
- Spring Data JPA
- postgreSQL

## Run

cd payment-service
mvn clean install
mvn spring-boot:run

Runs at:
http://localhost:8083


application.properties

spring.application.name=project 

spring.datasource.url=jdbc:postgresql://localhost:5432/paymentdb
spring.datasource.username=postgres
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

Payment Table

payment_id     PK
booking_id     FK
amount         DOUBLE
status         VARCHAR
transactionRef VARCHAR
createdAt      TIMESTAMP



APIs
POST /payment/process
Request:

{
  "bookingId": 4,
  "amount": 100
}
Response:

{
  "paymentId": 5,
  "status": "SUCCESS",
  "transactionRef": "UUID...",
  "amount": 100
}
GET /payment/{id}



