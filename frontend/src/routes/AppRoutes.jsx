import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "../pages/Login.jsx";

export default function AppRoutes() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
      </Routes>
    </Router>
  );
}
