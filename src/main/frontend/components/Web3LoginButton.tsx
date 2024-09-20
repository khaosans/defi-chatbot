import React, { useState, useEffect } from 'react';
import { getWalletAddress } from '../generated/Web3Service'; // Ensure the import path is correct
import { ethers } from 'ethers'; // Ensure ethers is imported

const Web3LoginButton: React.FC = () => {
  const [userAddress, setUserAddress] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    const fetchWalletAddress = async () => {
      try {
        const address = await getWalletAddress(); // Call the service method
        if (address) {
          setUserAddress(address); // Set the user address if fetched
        }
      } catch (error) {
        console.error("Error fetching wallet address:", error);
      }
    };
    fetchWalletAddress();
  }, []);

  const handleConnectWallet = async () => {
    setLoading(true);
    try {
      // Check if window.ethereum is available
      if (typeof window !== 'undefined' && window.ethereum) {
        const provider = new ethers.providers.Web3Provider(window.ethereum);
        await provider.send("eth_requestAccounts", []);
        const signer = provider.getSigner();
        const address = await signer.getAddress();
        setUserAddress(address); // Set the user address after connection
      } else {
        console.error("Ethereum provider is not available. Please install MetaMask.");
      }
    } catch (error) {
      console.error("Error connecting to wallet:", error);
    } finally {
      setLoading(false);
    }
  };

  const handleLogout = () => {
    setUserAddress(null);
    console.log("Disconnected from wallet");
  };

  return (
    <div>
      <button 
        onClick={userAddress ? handleLogout : handleConnectWallet} 
        disabled={loading}
      >
        {loading ? 'Connecting...' : (userAddress ? 'Disconnect Wallet' : 'Connect Wallet')}
      </button>
      {userAddress && (
        <p>Connected Wallet: {userAddress}</p>
      )}
    </div>
  );
};

export default Web3LoginButton;
