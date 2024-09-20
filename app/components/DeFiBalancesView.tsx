import React, { useEffect, useState } from 'react';
import axios from 'axios';

interface TokenBalance {
    chain: string;
    token: string;
    balance: number;
}

const DeFiBalancesView = () => {
    const [balances, setBalances] = useState<TokenBalance[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchBalances = async () => {
            try {
                const response = await axios.get('/api/defi/balances/user123');
                setBalances(response.data);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching balances:', error);
                setLoading(false);
            }
        };
        fetchBalances();
    }, []);

    const asciiArt = `
         _______  _______  _______  _______ 
        (  ____ \(  ___  )(       )(  ____ \\
        | (    \/| (   ) || () () || (    \/
        | (__    | (___) || || || || (__    
        |  __)   |  ___  || |(_)| ||  __)   
        | (      | (   ) || |   | || (      
        | (____/\| )   ( || )   ( || (____/\\
        (_______/|/     \||/     \|(_______/
    `;

    if (loading) {
        return <div><pre>{asciiArt}</pre>Loading balances...</div>;
    }

    return (
        <div>
            <h1>DeFi Account Balances</h1>
            <table>
                <thead>
                    <tr>
                        <th>Chain</th>
                        <th>Token</th>
                        <th>Balance (USD)</th>
                    </tr>
                </thead>
                <tbody>
                    {balances.map(balance => (
                        <tr key={`${balance.chain}-${balance.token}`}>
                            <td>{balance.chain}</td>
                            <td>{balance.token}</td>
                            <td>{balance.balance.toFixed(2)}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default DeFiBalancesView;