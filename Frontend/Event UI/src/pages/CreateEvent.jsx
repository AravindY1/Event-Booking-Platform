import React from "react";
import api from "../api/apiClient";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";

// Validation rules
const schema = yup.object().shape({
  name: yup.string().required("Event name is required"),
  description: yup.string().required("Description is required"),
  eventDate: yup.string().required("Event date is required"),
  price: yup.number().positive().required("Price is required"),
  totalSeats: yup.number().positive().integer().required("Seats required"),
});

export default function CreateEvent() {
  const nav = useNavigate();

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(schema),
  });

  const submit = async (data) => {
    try {
      const res = await api.post("http://localhost:8081/events/create", data);
      reset();
      nav(`/events/${res.data.eventId}`);
    } catch (error) {
      alert(error?.response?.data?.message || "Create event failed");
    }
  };

  return (
    <div className="max-w-lg bg-white p-8 rounded shadow">
      <h2 className="text-2xl font-semibold mb-6">Create New Event</h2>

      <form onSubmit={handleSubmit(submit)} className="space-y-4">
        <div>
          <input {...register("name")} placeholder="Event Name" className="w-full p-3 border rounded" />
          {errors.name && <p className="text-red-600 text-sm">{errors.name.message}</p>}
        </div>

        <div>
          <textarea
            {...register("description")}
            placeholder="Description"
            className="w-full p-3 border rounded h-24"
          />
          {errors.description && <p className="text-red-600 text-sm">{errors.description.message}</p>}
        </div>

        <div>
          <input {...register("eventDate")} placeholder="YYYY-MM-DDTHH:mm:ss" className="w-full p-3 border rounded" />
          {errors.eventDate && <p className="text-red-600 text-sm">{errors.eventDate.message}</p>}
        </div>

        <div>
          <input type="number" {...register("price")} placeholder="Price" className="w-full p-3 border rounded" />
          {errors.price && <p className="text-red-600 text-sm">{errors.price.message}</p>}
        </div>

        <div>
          <input
            type="number"
            {...register("totalSeats")}
            placeholder="Total Seats"
            className="w-full p-3 border rounded"
          />
          {errors.totalSeats && <p className="text-red-600 text-sm">{errors.totalSeats.message}</p>}
        </div>

        <button className="w-full py-3 bg-blue-600 text-white rounded">Create Event</button>
      </form>
    </div>
  );
}
