import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainLayout from './views/MainLayout';
import Logout from './views/Logout'; // Ensure this path is correct

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainLayout />} />
        <Route path="/logout" element={<Logout />} /> {/* Define the logout route */}
      </Routes>
    </Router>
  );
};

export default App;