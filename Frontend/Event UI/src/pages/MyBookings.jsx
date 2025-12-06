import React, { useEffect, useState } from "react";
import api from "../api/apiClient";
import { Link } from "react-router-dom";

export default function MyBookings() {
  const [bookings, setBookings] = useState([]);
  const [err, setErr] = useState("");

  useEffect(() => {
    const load = async () => {
      try {
        const res = await api.get("http://localhost:8082/bookings/myBookings");
        setBookings(res.data);
      } catch (e) {
        setErr("Could not load bookings");
      }
    };
    load();
  }, []);

  const cancel = async (id) => {
    try {
      await api.delete(`http://localhost:8082/bookings/cancel/${id}`);
      setBookings(b => b.filter(x => x.bookingId !== id));
    } catch {
      setErr("Cancel failed");
    }
  };

  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">My Bookings</h1>
       {err && <div className="text-red-600 mb-2">{err}</div>}

      {bookings.length === 0 ? (
        <div className="text-center py-10 text-gray-500 bg-white rounded shadow">
          No bookings made yet.
        </div>
      ) : (
        <div className="space-y-3">
          {bookings.map((b) => (
            <div
              key={b.bookingId}
              className="bg-white p-4 rounded shadow flex justify-between items-center"
            >
              <div>
                <div>Event: {b.eventId}</div>
                <div>Qty: {b.quantity} — Total: ${b.totalPrice}</div>
                <div className="text-gray-500 text-sm">
                  Date: {b.bookingDate}
                </div>
              </div>

              <div className="flex flex-col gap-2 items-end">
                <Link
                  to={`/payment/${b.bookingId}`}
                  className="text-blue-600 text-sm"
                >
                  Pay / Retry
                </Link>
                <button
                  onClick={() => cancel(b.bookingId)}
                  className="text-red-600 text-sm"
                >
                  Cancel
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
