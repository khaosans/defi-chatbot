import React from 'react';

const Logout: React.FC = () => {
    const handleLogout = async () => {
        console.log("Logout button clicked"); // Add this line for debugging
        try {
            // Make an API call to the backend logout endpoint
            const response = await fetch('/api/logout', { // Adjust the endpoint as needed
                method: 'POST',
                credentials: 'include', // Include cookies for session management
            });

            if (response.ok) {
                console.log("User logged out");
                // Redirect to login page or home page
                window.location.href = '/login'; // Adjust the path as needed
            } else {
                console.error("Logout failed");
                // Handle logout failure (e.g., show an error message)
            }
        } catch (error) {
            console.error("Error during logout:", error);
            // Handle network errors or other issues
        }
    };

    return (
        <button onClick={handleLogout}>
            Logout
        </button>
    );
};

export default Logout;