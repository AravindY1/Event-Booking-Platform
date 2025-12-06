import React, { useEffect, useState, useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../api/apiClient";
import { AuthContext } from "../contexts/AuthContext";

export default function EventDetails() {
  const { id } = useParams();
  const [event, setEvent] = useState(null);
  const [qty, setQty] = useState(1);
  const [err, setErr] = useState("");
  const { user } = useContext(AuthContext);
  const nav = useNavigate();

  useEffect(() => {
    const load = async () => {
      try {
        const res = await api.get(`http://localhost:8081/events/id/${id}`);
        setEvent(res.data);
      } catch (e) {
        setErr("Could not load event");
      }
    };
    load();
  }, [id]);

  const bookNow = async () => {
    setErr("");
    if (!user) {
      nav("/login");
      return;
    }
    try {
      const res = await api.post("http://localhost:8082/bookings/create", {
        eventId: Number(id),
        quantity: Number(qty)
      });
      nav(`/payment/${res.data.bookingId}`);
    } catch (e) {
      setErr(e?.response?.data || "Booking failed");
    }
  };

  if (!event) return <div>Loading...</div>;

  return (
    <div className="bg-white p-6 rounded shadow max-w-3xl">
      <h2 className="text-2xl font-bold mb-2">{event.name}</h2>
      <p className="text-muted mb-2">{event.description}</p>
      <p className="mb-2">Date: {event.eventDate}</p>
      <p className="mb-4 font-semibold">Price: ${event.price}</p>

      <div className="flex items-center gap-3">
        <input type="number" min="1" value={qty} onChange={e => setQty(parseInt(e.target.value) || 1)} className="w-24 p-2 border rounded" />
        <button onClick={bookNow} className="px-4 py-2 bg-green-600 text-white rounded">Book Now</button>
      </div>

      {err && <div className="text-red-600 mt-3">{err}</div>}
    </div>
  );
}
