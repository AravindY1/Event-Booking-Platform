import React, { useState, useEffect } from "react";

export default function EditEventModal({ open, onClose, eventData, onSave }) {
  const [form, setForm] = useState({
    name: "",
    description: "",
    eventDate: "",
    price: "",
    totalSeats: "",
  });

  // Load event data into form when modal opens
  useEffect(() => {
    if (eventData) {
      setForm({
        name: eventData.name,
        description: eventData.description,
        eventDate: eventData.eventDate,
        price: eventData.price,
        totalSeats: eventData.totalSeats,
      });
    }
  }, [eventData]);

  if (!open) return null;

  const handleChange = (e) =>
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));

  const handleSubmit = () => {
    onSave(form); // send updated data to AdminDashboard
  };

  return (
    <div className="fixed inset-0 bg-black/40 flex justify-center items-center z-40">
      <div className="bg-white p-6 rounded shadow-xl w-full max-w-lg animate-fade-in">
        <h2 className="text-xl font-semibold mb-4">Edit Event</h2>

        {/* FORM */}
        <div className="space-y-3">
          <input
            name="name"
            value={form.name}
            onChange={handleChange}
            placeholder="Event Name"
            className="w-full p-3 border rounded"
          />
          <textarea
            name="description"
            value={form.description}
            onChange={handleChange}
            placeholder="Description"
            className="w-full p-3 border rounded h-24"
          />
          <input
            name="eventDate"
            value={form.eventDate}
            onChange={handleChange}
            placeholder="YYYY-MM-DDTHH:mm:ss"
            className="w-full p-3 border rounded"
          />
          <input
            type="number"
            name="price"
            value={form.price}
            onChange={handleChange}
            placeholder="Price"
            className="w-full p-3 border rounded"
          />
          <input
            type="number"
            name="totalSeats"
            value={form.totalSeats}
            onChange={handleChange}
            placeholder="Total Seats"
            className="w-full p-3 border rounded"
          />
        </div>

        {/* BUTTONS */}
        <div className="flex justify-end gap-3 mt-6">
          <button onClick={onClose} className="px-4 py-2 bg-gray-200 rounded">
            Cancel
          </button>
          <button
            onClick={handleSubmit}
            className="px-4 py-2 bg-blue-600 text-white rounded"
          >
            Save
          </button>
        </div>
      </div>
    </div>
  );
}
