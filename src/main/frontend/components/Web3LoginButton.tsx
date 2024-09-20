import React, { useState, useEffect } from 'react';

const Web3LoginButton: React.FC = () => {
  const [userAddress, setUserAddress] = useState<string | null>(null);

  useEffect(() => {
    // Fetch wallet address from the API on component mount
    const fetchWalletAddress = async () => {
      const response = await fetch('/api/web3/address');
      const address = await response.text();
      if (address) {
        setUserAddress(address);
      }
    };
    fetchWalletAddress();
  }, []);

  const handleConnectWallet = async () => {
    const response = await fetch('/api/web3/connect');
    const address = await response.text();
    setUserAddress(address);
  };

  const handleLogout = () => {
    setUserAddress(null);
    console.log("Disconnected from wallet");
  };

  return (
    <div>
      <button 
        onClick={userAddress ? handleLogout : handleConnectWallet} 
        className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded transition duration-150 ease-in-out"
      >
        {userAddress ? `Disconnect Wallet` : 'Connect Wallet'}
      </button>
      {userAddress && <p>Connected: {userAddress}</p>}
    </div>
  );
};

export default Web3LoginButton;