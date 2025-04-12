import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Login from "./components/Login";
import Signup from "./components/Signup";
import Customize from "./pages/Customize";

const App: React.FC = () => {
  const isLoggedIn = !!localStorage.getItem("jwt"); // replace with real auth logic

  return (
    <Router>
      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/brand-setting" element={<Customize />} />
        <Route path="/" element={isLoggedIn ? <Dashboard /> : <Login />} />
      </Routes>
    </Router>
  );
};

export default App;
