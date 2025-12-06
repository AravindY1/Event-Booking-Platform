// import React, { useState, useContext } from "react";
// import api from "../api/apiClient";
// import { useNavigate } from "react-router-dom";
// import { AuthContext } from "../contexts/AuthContext";

// export default function Register() {
//   const [form, setForm] = useState({ fullName: "", email: "", password: "", role: "customer" });
//   const [err, setErr] = useState("");
//   const nav = useNavigate();
//   const { login } = useContext(AuthContext);

//   const submit = async (e) => {
//     e.preventDefault();
//     setErr("");
//     try {
//       const res = await api.post("http://localhost:8080/auth/register", form);
//       // support both JSON token and raw string token
//       const token = res.data?.token || (typeof res.data === "string" ? res.data.split(" ").pop() : null);
//       if (token) login(token, { email: form.email, fullName: form.fullName, role: form.role });
//       nav("/");
//     } catch (error) {
//       setErr(error?.response?.data || "Registration failed");
//     }
//   };

//   return (
//     <div className="max-w-md mx-auto mt-16 bg-white p-8 rounded shadow">
//       <h2 className="text-2xl font-semibold mb-6 text-center">Register</h2>
//       {err && <p className="text-red-600 text-center mb-4">{err}</p>}

//       <form onSubmit={submit} className="space-y-4">
//         <input type="text" placeholder="Full Name" value={form.fullName} onChange={e => setForm({...form, fullName: e.target.value})} className="w-full p-3 border rounded" />
//         <input type="email" placeholder="Email" value={form.email} onChange={e => setForm({...form, email: e.target.value})} className="w-full p-3 border rounded" />
//         <input type="password" placeholder="Password" value={form.password} onChange={e => setForm({...form, password: e.target.value})} className="w-full p-3 border rounded" />
//         <select value={form.role} onChange={e => setForm({...form, role: e.target.value})} className="w-full p-3 border rounded">
//           <option value="customer">Customer</option>
//           <option value="admin">Admin</option>
//         </select>
//         <button type="submit" className="w-full py-3 bg-blue-600 text-white rounded">Register</button>
//       </form>
//     </div>
//   );
// }


import React, { useContext } from "react";
import api from "../api/apiClient";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";

const schema = yup.object().shape({
  fullName: yup.string().required("Full name required"),
  email: yup.string().email().required("Email required"),
  password: yup.string().min(4).required("Password required"),
  role: yup.string().required(),
});

export default function Register() {
  const nav = useNavigate();
  const { login } = useContext(AuthContext);

  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const submit = async (data) => {
    try {
      const res = await api.post("http://localhost:8080/auth/register", data);
      const token = res.data.token;
      login(token, {
        email: data.email,
        fullName: data.fullName,
        role: data.role,
      });
      nav("/");
    } catch (error) {
      alert(error?.response?.data || "Registration failed");
    }
  };

  return (
    <div className="max-w-md mx-auto mt-16 bg-white p-8 rounded shadow">
      <h2 className="text-2xl font-semibold mb-6 text-center">Register</h2>

      <form onSubmit={handleSubmit(submit)} className="space-y-4">
        <div>
          <input {...register("fullName")} placeholder="Full Name" className="w-full p-3 border rounded" />
          {errors.fullName && <p className="text-red-600 text-sm">{errors.fullName.message}</p>}
        </div>

        <div>
          <input {...register("email")} placeholder="Email" className="w-full p-3 border rounded" />
          {errors.email && <p className="text-red-600 text-sm">{errors.email.message}</p>}
        </div>

        <div>
          <input type="password" {...register("password")} placeholder="Password" className="w-full p-3 border rounded" />
          {errors.password && <p className="text-red-600 text-sm">{errors.password.message}</p>}
        </div>

        <div>
          <select {...register("role")} className="w-full p-3 border rounded">
            <option value="customer">Customer</option>
            <option value="admin">Admin</option>
          </select>
        </div>

        <button className="w-full py-3 bg-blue-600 text-white rounded">Register</button>
      </form>
    </div>
  );
}
