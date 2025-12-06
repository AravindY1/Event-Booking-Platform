**Event Booking Platform — Microservices + React + JWT**

A full-stack Event Booking Platform built using:

**Spring Boot Microservices**

**React.js Frontend**

**JWT Authentication**

**PostgreSQL Databases**

**Email Service (SMTP)**

**Payment Service (Mock Payment)**


Users can browse events, book tickets, make payments, and receive email confirmations.
Admins can create, update, and delete events.


**System Architecture**

                           ┌─────────────────────┐
                           │     React UI        │
                           │  (localhost:3000)   │
                           └─────────┬───────────┘
                                     │ REST API
                                     ▼
     ┌─────────────────────────────────────────────────────────────┐
     │                       Backend Services                       │
     ├─────────────────────────────────────────────────────────────┤
     │  Auth Service       — 8080  — JWT Login/Signup              │
     │  Event Service      — 8081  — Admin Event CRUD              │
     │  Booking Service    — 8082  — Create/View/Cancel Bookings   │
     │  Payment Service    — 8083  — Payment Processing            │
     │  Email Service      — 8084  — Email Notifications           │
     └─────────────────────────────────────────────────────────────┘


 **Features**
**User Features**

✔ Signup & Login using JWT

✔ Browse all events

✔ Book tickets

✔ View personal bookings

✔ Make payments

✔ Receive confirmation emails

**Admin Features**

✔ Create events

✔ Edit events

✔ Delete events

✔ View all bookings (optional extension)


**Technology Stack**

**Backend — Spring Boot 3.5.7**

Spring Web

Spring Data JPA

Spring Security + JWT

PostgreSQL

Lombok

ModelMapper

JavaMailSender

Feign Clients (optional)

**Frontend — React.js**

React Router

Axios

JWT decode

Context API (optional)

**Database**

PostgreSQL 18

pgAdmin 4

**Project Structure**


event-booking-platform/

│

├── auth-service/          # Login, signup, JWT

├── event-service/         # Event CRUD

├── booking-service/       # Bookings

├── payment-service/       # Payment logic

├── email-service/         # Sends confirmation emails
│
└── frontend-react/        # React user interface

**Installation & Setup**
 **Install Required Software**


| Tool        | Version          |
| ----------- | ---------------- |
| Java        | 17               |
| Spring Boot | 3.5.7            |
| PostgreSQL  | 18               |
| Node.js     | 18+              |
| IDEs        | Eclipse, VS Code |
| REST Tool   | Postman          |


**Database Setup (for all microservices)**

Open **pgAdmin**

Create separate databases:

authdb

eventdb

bookingdb

paymentdb



Update each microservice's application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/eventdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


**API Endpoints**
**Auth Service (8080)**

**Method	Endpoint	Description**

POST	  /auth/signup	Register

POST	  /auth/login	   Login


**Event Service (8081)**

**Method	Endpoint	    Description**

GET	     /events	      Get all events

POST	   /events	      Create event (Admin)

PUT	     /events/{id}	  Update event (Admin)

DELETE	 /events/{id}	   Delete event (Admin)


**Booking Service (8082)**

Method	 Endpoint	         Description

POST  	/bookings/{eventId}	 Book event

GET	   /bookings/myBookings	 User’s bookings

DELETE	/bookings/{id}	    Cancel booking


**Payment Service (8083)**

| POST | /payment/pay | Make payment |

**Email Service (8084)**

| POST | /email/send | Send booking confirmation |


**Running the Project**
**Run Backend (Eclipse/IntelliJ)**

**Start microservices in this order:**

Auth Service (8080)
Event Service (8081)
Booking Service (8082)
Payment Service (8083)
Email Service (8084)



**Run Frontend (VS Code)**

cd frontend-react

npm install

npm start



Frontend runs on:

http://localhost:3000


**Frontend Pages**

Page	                Description

/login	              User login

/signup	              User registration

/	                    Home page — list of events

/events/:id/book	     Book an event

/my-bookings	         User bookings

/admin/events	          Admin event dashboard


**Testing with Postman**

Collection includes:

Signup/Login

Create Event

Book Event

Payment

Email trigger



**Future Enhancements**

✔ Search events

✔ Filter by category/date

✔ Payment gateway integration (Stripe/Razorpay)

✔ Notification microservice via WebSockets

✔ Kubernetes deployment
