


# Email Service (Spring Boot)

Sends registration and booking confirmation emails.

## Tech Stack
- Spring Boot
- Spring Mail
- REST API

## Run

cd email-service
mvn clean install
mvn spring-boot:run


Runs at:
http://localhost:8084

Environment Variables

spring.mail.host=smtp.gmail.com
spring.mail.username=your@gmail.com
spring.mail.password=app-password
spring.mail.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true



Endpoints
POST /email/send

{
  "to": "user@example.com",
  "subject": "Welcome!",
  "body": "Hello and welcome..."
}

Called By
	•	Auth Service → Welcome Email
	•	Booking Service → Booking Confirmation





