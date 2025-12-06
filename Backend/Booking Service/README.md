
# Booking Service (Spring Boot)

Handles event bookings, cancellations, and user booking history.

##Tech Stack
- Spring Boot
- Spring Data JPA
- OpenFeign (to call Event Service)
- Spring Security + JWT
- postgreSQL

##Run

cd booking-service
mvn clean install
mvn spring-boot:run


Runs at:
http://localhost:8082


application.properties

spring.application.name=project 

spring.datasource.url=jdbc:postgresql://localhost:5432/bookingdb
spring.datasource.username=postgres
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect


Booking Table

booking_id      PK
event_id        FK
user_email      VARCHAR
quantity        INT
total_price     DOUBLE
booking_date    TIMESTAMP



APIs
POST /bookings/create
Authenticated users only.
Request

{
  "eventId": 1,
  "quantity": 2
}
GET /bookings/myBookings
Returns bookings for logged-in user.
DELETE /bookings/cancel/{id}
GET /admin/totalBookings (ADMIN)
Response:

{ "count": 8 }

Communication Flow
Booking service calls Event Service:

createBooking()  
 → GET /events/id/{id}
 → POST /events/internal/updateSeats
If booking fails → seats restored.



