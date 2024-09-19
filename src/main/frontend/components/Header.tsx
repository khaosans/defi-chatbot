import React from 'react';
import { useNavigate } from 'react-router-dom'; // Use useNavigate for navigation
import { Icon } from '@vaadin/react-components'; // Ensure you have the correct import for icons
import { ContextMenu } from '@vaadin/react-components/ContextMenu'; // Import ContextMenu for dropdown
// import './styles/shared-styles.css'; // Removed this line

const Header: React.FC = () => {
    const navigate = useNavigate(); // Use useNavigate for navigation

    const handleLogout = async () => {
        // Clear user data from local storage
        localStorage.removeItem('user'); // Clear user data from local storage
        await fetch('/api/logout', { method: 'POST' }); // Call backend logout endpoint
        navigate('/logout'); // Redirect to logout page
    };

    return (
        <header className="fixed top-0 left-0 right-0 bg-gray-800 text-white p-4 flex justify-between items-center z-50">
            <h1 className="text-xl">My Application</h1>
            <div className="flex items-center ml-auto"> {/* Added ml-auto to push the button to the right */}
                <ContextMenu>
                    <Icon icon="cog" className="text-white cursor-pointer" />
                    <div>
                        <button onClick={handleLogout} className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
                            Logout
                        </button>
                    </div>
                </ContextMenu>
            </div>
        </header>
    );
};

export default Header;