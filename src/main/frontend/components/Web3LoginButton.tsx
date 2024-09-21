import React, { useState, useEffect } from 'react';
import { ethers } from 'ethers'; // Ensure ethers is imported correctly
const Web3LoginButton: React.FC = () => {
  const [userAddress, setUserAddress] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  // Fetch wallet address if already connected (on page load)
  useEffect(() => {
    const fetchWalletAddress = async () => {
      if (typeof window !== 'undefined' && window.ethereum) {
        try {
          const provider = new ethers.BrowserProvider(window.ethereum); // Adjusted for newer ethers version
          const signer = await provider.getSigner(); // Await the signer
          const address = await signer.getAddress(); // Now this should work
          setUserAddress(address); // Set the user address if already connected
        } catch (error) {
          console.error("Error fetching wallet address:", error);
        }
      }
    };
    fetchWalletAddress(); // Call the function
  }, []);

  // Handle connecting to the wallet
  const handleConnectWallet = async () => {
    setLoading(true);
    try {
      if (typeof window !== 'undefined' && window.ethereum) {
        const provider = new ethers.BrowserProvider(window.ethereum); // Adjusted for newer ethers version
        // Request wallet connection from user
        const accounts = await provider.send("eth_requestAccounts", []);
        if (accounts.length > 0) {
          const signer = await provider.getSigner(); // Await the signer
          const address = await signer.getAddress(); // Now this should work
          setUserAddress(address); // Set the user address after connection
        }
      } else {
        console.error("Ethereum provider is not available. Please install MetaMask.");
      }
    } catch (error) {
      console.error("Error connecting to wallet:", error);
    } finally {
      setLoading(false);
    }
  };

  // Handle wallet disconnection (reset state, but MetaMask remains connected)
  const handleDisconnectWallet = () => {
    setUserAddress(null); // Clear the user address from state
    console.log("Disconnected from wallet.");
  };

  return (
    <div>
      <button 
        onClick={userAddress ? handleDisconnectWallet : handleConnectWallet} 
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