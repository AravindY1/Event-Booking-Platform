# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Babel](https://babeljs.io/) (or [oxc](https://oxc.rs) when used in [rolldown-vite](https://vite.dev/guide/rolldown)) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.



# Event Booking UI (React + Tailwind)

Frontend for the Event Booking microservices system.

## 🚀 Tech Stack
- React 18
- React Router v6
- Axios
- TailwindCSS
- Vite

## 📌 Run Instructions

### Install packages

cd event
npm install


Start dev server

npm run dev
App runs at:👉 http://localhost:3000

API Integration Endpoints
Frontend calls

| Feature                | Endpoint                                                                                   |
| ---------------------- | ------------------------------------------------------------------------------------------ |
| Login                  | POST [http://localhost:8080/auth/login](http://localhost:8080/auth/login)                  |
| Register               | POST [http://localhost:8080/auth/register](http://localhost:8080/auth/register)            |
| List Events            | GET [http://localhost:8081/events/all](http://localhost:8081/events/all)                   |
| Event Details          | GET [http://localhost:8081/events/id/{id}](http://localhost:8081/events/id/{id})           |
| Create Booking         | POST [http://localhost:8082/bookings/create](http://localhost:8082/bookings/create)        |
| My Bookings            | GET [http://localhost:8082/bookings/myBookings](http://localhost:8082/bookings/myBookings) |
| Payment                | POST [http://localhost:8083/payment/process](http://localhost:8083/payment/process)        |
| Admin — All Users      | GET [http://localhost:8080/auth/allUsers](http://localhost:8080/auth/allUsers)             |
| Admin — Total Bookings | GET [http://localhost:8082/admin/totalBookings](http://localhost:8082/admin/totalBookings) |


Features
	•	User Authentication (JWT)
	•	View all events
	•	Event details page
	•	Book events
	•	Cancel bookings
	•	Retry payments
	•	Admin Dashboard
	•	Create/update/delete events
	•	View users count & bookings count

Environment Variables
Create file:

VITE_API_BASE=http://localhost:8080

const base = import.meta.env.VITE_API_BASE;



