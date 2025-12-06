import React, { useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";

export default function Navbar() {
    const { user, logout, isAdmin } = useContext(AuthContext);
    const nav = useNavigate();

    const handleLogout = () => {
        logout();
        nav("/login");
    };

    return (
        <nav className="bg-white border-b">
            <div className="max-w-6xl mx-auto px-4 py-4 flex items-center justify-between">
                <div className="flex items-center gap-6">
                    <Link to="/" className="text-2xl font-semibold text-primary">
                        EventBook
                    </Link>
                    <Link to="/" className="text-sm text-muted hover:text-primary">
                        Events
                    </Link>
                </div>

                <div className="flex items-center gap-4">
                    {!user ? (
                        <>
                            <Link to="/login" className="text-sm text-muted hover:text-primary">
                                Login
                            </Link>
                            <Link to="/register" className="text-sm text-muted hover:text-primary">
                                Register
                            </Link>
                        </>
                    ) : (
                        <>
                            <Link to="/bookings" className="text-sm text-muted hover:text-primary">
                                My Bookings
                            </Link>

                            <Link to="/profile" className="text-sm text-muted hover:text-primary">
                                Profile
                            </Link>

                            {isAdmin() && (

                                <>
                                    <Link to="/admin/create" className="text-sm text-muted hover:text-primary">
                                        Create Event
                                    </Link>

                                    <Link to="/admin/dashboard" className="text-sm text-muted hover:text-primary">
                                        Dashboard
                                    </Link>
                                </>

                            )}
                            <button onClick={handleLogout} className="ml-2 px-3 py-1 bg-gray-100 rounded">
                                Logout ({user.fullName || user.email})
                            </button>
                        </>
                    )}
                </div>
            </div>
        </nav>
    );
}
