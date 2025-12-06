import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import EventsList from "./pages/EventsList";
import EventDetails from "./pages/EventDetails";
import CreateEvent from "./pages/CreateEvent";
import MyBookings from "./pages/MyBookings";
import Payment from "./pages/Payment";
import Login from "./pages/Login";
import Register from "./pages/Register";
import { AuthContext } from "./contexts/AuthContext";
import Profile from "./pages/Profile";
import AdminDashboard from "./pages/AdminDashboard";


function PrivateRoute({ children, adminOnly }) {
  const { user } = React.useContext(AuthContext);
  if (!user) return <Navigate to="/login" replace />;
  if (adminOnly && user.role !== "admin") return <Navigate to="/" replace />;
  return children;
}

export default function App() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <main className="max-w-6xl mx-auto px-4 py-8">
        <Routes>
          <Route path="/" element={<EventsList />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/events/:id" element={<EventDetails />} />
          <Route
            path="/admin/create"
            element={
              <PrivateRoute adminOnly>
                <CreateEvent />
              </PrivateRoute>
            }
          />

          <Route
            path="/profile"
            element={
              <PrivateRoute>
                <Profile />
              </PrivateRoute>
            }
          />

          <Route
            path="/bookings"
            element={
              <PrivateRoute>
                <MyBookings />
              </PrivateRoute>
            }
          />
          <Route
            path="/payment/:bookingId"
            element={
              <PrivateRoute>
                <Payment />
              </PrivateRoute>
            }
          />
          <Route
            path="/admin/dashboard"
            element={
              <PrivateRoute adminOnly>
                <AdminDashboard />
              </PrivateRoute>
            }
          />

        </Routes>
      </main>
    </div>
  );
}
