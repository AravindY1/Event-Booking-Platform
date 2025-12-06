import React, { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";

export default function Profile() {
  const { user, logout } = useContext(AuthContext);
  const nav = useNavigate();

  const handleLogout = () => {
    logout();
    nav("/login"); // redirect after logout
  };

  return (
    <div className="max-w-lg bg-white p-8 rounded shadow">
      <h2 className="text-3xl font-semibold mb-4">My Profile</h2>

      <div className="space-y-3 text-lg">
        <p><strong>Name:</strong> {user?.fullName}</p>
        <p><strong>Email:</strong> {user?.email}</p>
        <p><strong>Role:</strong> {user?.role}</p>
      </div>

      <button
        onClick={handleLogout}
        className="mt-6 px-5 py-2 bg-red-600 text-white rounded hover:bg-red-700"
      >
        Logout
      </button>
    </div>
  );
}
