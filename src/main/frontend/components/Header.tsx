import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "Frontend/themes/customer-support-agent/styles.css";
import Web3LoginButton from './Web3LoginButton';

interface HeaderProps {
    logoutUrl: string; // Define the prop type
}

const Header: React.FC<HeaderProps> = ({ logoutUrl }) => { // Accept the prop
    const navigate = useNavigate();
    const [selectedModel, setSelectedModel] = useState<string>('OpenAI/GPT-4o');

    // List of models with their versions
    const models = [
        'OpenAI/GPT-4o',
        'OpenAI/GPT-3.5',
        'Ollama/Llama3.1',
        'Ollama/Llama2.0',
    ];

    const handleModelChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedModel(event.target.value); // Update state with selected value
        console.log(`Selected model: ${event.target.value}`);
    };

    const handleLogout = async () => {
        const userSessionId = localStorage.getItem('sessionId');
        if (userSessionId) {
            try {
                await fetch(`${logoutUrl}/${userSessionId}`, { method: 'POST' }); // Use the logoutUrl prop
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
            <div className="header-actions">
                <Web3LoginButton />
                <button className="theme-toggle-button" onClick={toggleTheme}>
                    {document.documentElement.getAttribute('data-theme') === 'dark' ? '☀️' : '🌙'}
                </button>
                <button className="logout-button" onClick={handleLogout}>Logout</button>
                <div className="dropdown-container">
                    <label htmlFor="model-select">Select Model:</label>
                    <select
                        id="model-select"
                        value={selectedModel}
                        onChange={handleModelChange}
                        style={{ padding: '5px', borderRadius: '4px' }}
                    >
                        {models.map(model => (
                            <option key={model} value={model}>{model}</option>
                        ))}
                    </select>
                </div>
            </div>
        </header>
    );
};

export default Header;