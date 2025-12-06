import React, { useState, useContext } from "react";
import api from "../api/apiClient";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";

const schema = yup.object().shape({
  email: yup.string().email().required("Email is required"),
  password: yup.string().required("Password is required"),
});

export default function Login() {
  const { login } = useContext(AuthContext);
  const nav = useNavigate();

  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const submit = async (data) => {
    try {
      const res = await api.post("http://localhost:8080/auth/login", data);
      const token = res.data.token;
      login(token, {
        email: res.data.email,
        fullName: res.data.fullName,
        role: res.data.role,
      });
      nav("/");
    } catch (error) {
      alert(error?.response?.data || "Login failed");
    }
  };

  return (
    <div className="max-w-md mx-auto mt-16 bg-white p-8 rounded shadow">
      <h2 className="text-2xl font-semibold mb-6 text-center">Login</h2>

      <form onSubmit={handleSubmit(submit)} className="space-y-4">
        <div>
          <input {...register("email")} placeholder="Email" className="w-full p-3 border rounded" />
          {errors.email && <p className="text-red-600 text-sm">{errors.email.message}</p>}
        </div>

        <div>
          <input type="password" {...register("password")} placeholder="Password" className="w-full p-3 border rounded" />
          {errors.password && <p className="text-red-600 text-sm">{errors.password.message}</p>}
        </div>

        <button className="w-full py-3 bg-blue-600 text-white rounded">Login</button>
      </form>
    </div>
  );
}
