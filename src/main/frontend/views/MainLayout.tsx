import React from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for navigation
// Check if ChatHistory.tsx exists in the same directory
import ChatHistory from './ChatHistoryView'; // Ensure this path is correct
// Check if ChatComponent.tsx exists in the same directory
import ChatComponent from './ChatComponentView'; // Ensure this path is correct

const MainLayout: React.FC = () => {
  const navigate = useNavigate(); // Initialize navigate for navigation

  const handleLogout = () => {
    navigate('/logout'); // Navigate to the /logout page
  };

  return (
    <div className="main-layout">
      <div className="sidebar">
        <ChatHistory />
        <button onClick={handleLogout}>Logout</button> {/* Logout button */}
      </div>
      <div className="chat-area">
        <ChatComponent />
      </div>
    </div>
  );
};

// Add styles for layout
const styles = {
  mainLayout: {
    display: 'flex',
    height: '100vh',
  },
  sidebar: {
    width: '300px', // Adjust width as needed
    borderRight: '1px solid #ccc',
  },
  chatArea: {
    flex: 1,
    display: 'flex',
    flexDirection: 'column',
  },
};

export default MainLayout;