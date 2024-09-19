import React from 'react';
import { useNavigate } from 'react-router-dom';
import "Frontend/themes/customer-support-agent/styles.css";

const Header: React.FC = () => {
    const navigate = useNavigate();

    const handleLogout = async () => {
        const userSessionId = localStorage.getItem('sessionId');
        if (userSessionId) {
            try {
                await fetch(`/api/logout/${userSessionId}`, { method: 'POST' });
                localStorage.removeItem('sessionId');
                navigate('/logout');
            } catch (error) {
                console.error("Logout failed:", error);
            }
        }
    };

    const toggleTheme = () => {
        const currentTheme = document.documentElement.getAttribute('data-theme');
        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
        document.documentElement.setAttribute('data-theme', newTheme);
    };

    return (
        <header className="header-container">
            <h1 className="header-title">SourBot</h1>
            <div>
                <button className="theme-toggle-button" onClick={toggleTheme}>
                    {document.documentElement.getAttribute('data-theme') === 'dark' ? '☀️' : '🌙'}
                </button>
                <button className="logout-button" onClick={handleLogout}>Logout</button>
            </div>
        </header>
    );
};

export default Header;