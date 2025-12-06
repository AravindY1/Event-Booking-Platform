import React, { useEffect, useState } from "react";
import api from "../api/apiClient";
import { Link } from "react-router-dom";
import EditEventModal from "../components/EditEventModal";


export default function AdminDashboard() {

  const [editModalOpen, setEditModalOpen] = useState(false);
  const [selectedEvent, setSelectedEvent] = useState(null);
  const [events, setEvents] = useState([]);
  const [stats, setStats] = useState({
    totalEvents: 0,
    totalBookings: 0,
    totalUsers: 0,
  });

  const [err, setErr] = useState("");

  useEffect(() => {
    loadDashboard();
  }, []);
  const loadDashboard = async () => {
    try {
      // Fetch events list
      const eventsRes = await api.get("http://localhost:8081/events/all");
      const eventsList = eventsRes.data;
      setEvents(eventsList);

      // Fetch total bookings
      const bookingsRes = await api.get("http://localhost:8082/admin/totalBookings");
      const totalBookings = bookingsRes.data.count;

      // Fetch total users
      const usersRes = await api.get("http://localhost:8080/auth/allUsers");
      const users = usersRes.data;

      // Update stats
      setStats({
        totalEvents: eventsList.length,
        totalBookings: totalBookings,
        totalUsers: users.length,
      });

    } catch (e) {
      console.log(e);
      setErr("Could not load admin dashboard data");
    }
  };

  const updateEventModal = (event) => {
    setSelectedEvent(event);
    setEditModalOpen(true);
  };


  const saveUpdatedEvent = async (updatedData) => {
    try {
      const res = await api.put(
        `http://localhost:8081/events/update/${selectedEvent.eventId}`,
        updatedData
      );

      const updatedEvent = res.data;

      setEvents((prev) =>
        prev.map((e) =>
          e.eventId === updatedEvent.eventId ? updatedEvent : e
        )
      );

      setEditModalOpen(false);
      alert("Event updated successfully.");
    } catch (error) {
      console.error(error);
      alert("Update failed");
    }
  };


  const deleteEvent = async (id) => {
    if (!confirm("Delete this event?")) return;

    try {
      await api.delete(`http://localhost:8081/events/delete/${id}`);
      setEvents(events.filter((e) => e.eventId !== id));
    } catch (e) {
      alert("Delete failed");
    }
  };

  return (
    <div>

      <h1 className="text-4xl font-bold mb-6">Admin Dashboard</h1>

      {err && <p className="text-red-600">{err}</p>}

      {/* SUMMARY CARDS */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">

        <div className="bg-white shadow p-6 rounded">
          <h2 className="text-gray-500 text-sm uppercase">Total Events</h2>
          <p className="text-3xl font-semibold">{stats.totalEvents}</p>
        </div>

        <div className="bg-white shadow p-6 rounded">
          <h2 className="text-gray-500 text-sm uppercase">Total Bookings</h2>
          <p className="text-3xl font-semibold">{stats.totalBookings}</p>
        </div>

        <div className="bg-white shadow p-6 rounded">
          <h2 className="text-gray-500 text-sm uppercase">Total Users</h2>
          <p className="text-3xl font-semibold">{stats.totalUsers}</p>
        </div>

      </div>

      {/* Header Row */}
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-2xl font-semibold">Manage Events</h2>
        <Link
          to="/admin/create"
          className="px-4 py-2 bg-blue-600 text-white rounded"
        >
          + Create Event
        </Link>
      </div>

      {/* EVENT TABLE */}
      <div className="overflow-x-auto bg-white rounded shadow">
        <table className="min-w-full text-sm">
          <thead className="bg-gray-100">
            <tr>
              <th className="py-3 px-4 text-left">ID</th>
              <th className="py-3 px-4 text-left">Event</th>
              <th className="py-3 px-4 text-left">Price</th>
              <th className="py-3 px-4 text-left">Seats</th>
              <th className="py-3 px-4 text-left">Actions</th>
            </tr>
          </thead>

          <tbody>
            {events.map((event) => (
              <tr key={event.eventId} className="border-b hover:bg-gray-50">
                <td className="py-3 px-4">{event.eventId}</td>

                <td className="py-3 px-4 font-medium">{event.name}</td>

                <td className="py-3 px-4">${event.price}</td>

                <td className="py-3 px-4">{event.totalSeats}</td>

                <td className="py-3 px-4 flex gap-3">

                  <Link
                    to={`/events/${event.eventId}`}
                    className="text-blue-600"
                  >
                    View
                  </Link>
                  <button
                    onClick={() => updateEventModal(event)}
                    className="text-green-600"
                  >
                    Update
                  </button>

                  <button
                    onClick={() => deleteEvent(event.eventId)}
                    className="text-red-600"
                  >
                    Delete
                  </button>

                </td>
              </tr>
            ))}
          </tbody>

        </table>
      </div>

      <EditEventModal
        open={editModalOpen}
        onClose={() => setEditModalOpen(false)}
        eventData={selectedEvent}
        onSave={saveUpdatedEvent}
      />


    </div>
  );
}
