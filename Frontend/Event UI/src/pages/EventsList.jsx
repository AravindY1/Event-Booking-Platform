import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../api/apiClient";

export default function EventsList() {
  const [events, setEvents] = useState([]);
  const [err, setErr] = useState("");

  useEffect(() => {
    const load = async () => {
      try {
        const res = await api.get("http://localhost:8081/events/all");
        setEvents(res.data);
      } catch (e) {
        setErr("Unable to load events");
      }
    };
    load();
  }, []);

  return (
    <div>
      <h1 className="text-4xl font-bold mb-6">Events</h1>
      {err && <div className="text-red-600 mb-3">{err}</div>}

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {events.map(ev => (
          <div key={ev.eventId} className="bg-white p-6 rounded shadow">
            <h3 className="text-xl font-semibold mb-2">{ev.name}</h3>
            <p className="text-muted mb-3">{ev.description}</p>
            <div className="flex items-center justify-between">
              <div className="text-lg font-medium">${ev.price}</div>
              <Link to={`/events/${ev.eventId}`} className="text-primary text-sm">View</Link>
            </div>
          </div>
        ))}
        {events.length === 0 && !err && <div>No events yet</div>}
      </div>
    </div>
  );
}
