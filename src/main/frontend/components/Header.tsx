import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Use useNavigate for navigation
import { Icon } from '@vaadin/react-components'; // Ensure you have the correct import for icons
import { ContextMenu } from '@vaadin/react-components/ContextMenu'; // Import ContextMenu for dropdown

const Header: React.FC = () => {
    const navigate = useNavigate(); // Use useNavigate for navigation

    useEffect(() => {
        const userSessionId = localStorage.getItem('sessionId'); // Retrieve session ID from local storage
        if (userSessionId) {
            // Optionally, you can validate the session ID with the backend here
        }
    }, []);

    const handleLogout = async () => {
        const userSessionId = localStorage.getItem('sessionId'); // Retrieve session ID from local storage
        if (userSessionId) {
            try {
                await fetch(`/api/logout/${userSessionId}`, { method: 'POST' }); // Call backend logout endpoint with session ID
                localStorage.removeItem('sessionId'); // Clear session ID from local storage
                navigate('/logout'); // Redirect to logout page
            } catch (error) {
                console.error("Logout failed:", error);
                // Optionally show a notification for logout failure
            }
        }
    };

    return (
        <header className="fixed top-0 left-0 right-0 bg-gray-800 text-white p-4 flex justify-between items-center z-50 shadow-md transition-colors duration-300">
            <h1 className="text-xl lumo-header">My Application</h1> {/* Apply Lumo header style */}
            <div className="absolute top-4 right-4 flex items-center"> {/* Positioned absolute, top-right */}
                <ContextMenu>
                    <Icon icon="cog" className="text-white cursor-pointer hover:text-gray-300 transition-colors" aria-label="Settings" />
                </ContextMenu>
                <button 
                    onClick={handleLogout} 
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded lumo-button transition-colors" // Apply Lumo button style
                    aria-label="Logout"
                >
                    Logout
                </button>
            </div>
        </header>
    );
};

export default Header;