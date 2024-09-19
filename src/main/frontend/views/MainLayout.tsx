import React from 'react';
// Check if ChatHistory.tsx exists in the same directory
import ChatHistory from './ChatHistory'; // Ensure this path is correct
// Check if ChatComponent.tsx exists in the same directory
import ChatComponent from './ChatComponent'; // Ensure this path is correct

const MainLayout: React.FC = () => {
  return (
    <div className="main-layout">
      <div className="sidebar">
        <ChatHistory />
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