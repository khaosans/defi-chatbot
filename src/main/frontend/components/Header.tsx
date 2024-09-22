import React, { ReactNode } from 'react';
import "Frontend/themes/customer-support-agent/styles.css";
import CustomButton from './CustomButton'; // Import CustomButton
import Web3LoginButton from './Web3LoginButton';

interface HeaderProps {
    children?: ReactNode;
    logoutUrl: string; // Add logoutUrl prop
}

const Header: React.FC<HeaderProps> = ({ children, logoutUrl }) => {
    const handleLogout = () => {
        localStorage.removeItem('sessionId');
        window.location.href = logoutUrl; // Use the logoutUrl prop
    };

    const toggleTheme = () => {
        const currentTheme = document.documentElement.getAttribute('data-theme');
        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
        document.documentElement.setAttribute('data-theme', newTheme);
    };

    const handleHamburgerClick = () => {
        console.log("Hamburger menu clicked");
    };

    return (
        <header className="header-container">
            <h1 className="header-title">SourBot</h1>
            <div className="header-actions">
                <Web3LoginButton />
                <CustomButton onClick={toggleTheme} className="toggle-button" bgColor="bg-blue-500">
                    {document.documentElement.getAttribute('data-theme') === 'dark' ? '☀️' : '🌙'}
                </CustomButton>
                <CustomButton onClick={handleLogout} className="logout-button" bgColor="bg-blue-500">Logout</CustomButton>
                <CustomButton onClick={handleHamburgerClick} className="hamburger-button" bgColor="bg-blue-500">
                    &#9776; {/* Unicode character for hamburger icon */}
                </CustomButton>
            </div>
        </header>
    );
};

// Example styles (to be added in your CSS file):
// .header-title { font-size: 24px; color: #ffffff; margin: 0; padding: 10px; }
// .header-actions { display: flex; gap: 15px; padding: 10px; }
// .header-container { background-color: var(--header-bg-color); } // Set background color based on theme
// .toggle-button, .logout-button, .hamburger-button { color: #ffffff; } // Ensure text is visible in dark mode

export default Header;