import React, { useState, useEffect } from 'react';
import { getBalancesByChain, getTotalPortfolioValue, getTokenPrice, generatePortfolioInsights } from 'Frontend/generated/DeBankMockService';
import { Grid } from '@vaadin/react-components/Grid';
import { GridColumn } from '@vaadin/react-components/GridColumn';
import { Button } from '@vaadin/react-components/Button';
import { Select } from '@vaadin/react-components/Select';
import { Details } from '@vaadin/react-components/Details';

interface TokenBalance {
  token: string;
  balance: number;
}

export default function DeFiDashboardView() {
  const [balances, setBalances] = useState<TokenBalance[]>([]);
  const [totalValue, setTotalValue] = useState<number>(0);
  const [selectedChain, setSelectedChain] = useState<string>('ethereum');
  const [error, setError] = useState<string | null>(null);
  const [insights, setInsights] = useState<string[]>([]);

  const chains = ['ethereum', 'bsc', 'polygon'];

  const fetchData = async () => {
    try {
      const [balancesData, totalValueData, insightsData] = await Promise.all([
        getBalancesByChain(selectedChain),
        getTotalPortfolioValue(),
        generatePortfolioInsights(),
      ]);
      setBalances(balancesData);
      setTotalValue(totalValueData);
      setInsights(insightsData);
      setError(null);
    } catch (err) {
      setError('Failed to fetch DeFi account data. Please try again.');
    }
  };

  useEffect(() => {
    fetchData();
  }, [selectedChain]);

  const handleChainChange = (event: CustomEvent) => {
    setSelectedChain(event.detail.value);
  };

  return (
    <div>
      <h2>DeFi Portfolio Dashboard</h2>
      {error && <div className="error">{error}</div>}
      <Button onClick={fetchData}>Refresh Data</Button>
      <Select
        label="Select Chain"
        value={selectedChain}
        onChange={handleChainChange}
        items={chains.map(chain => ({ label: chain.toUpperCase(), value: chain }))}
      />
      <Grid items={balances}>
        <GridColumn path="token" header="Token" />
        <GridColumn path="balance" header="Balance" />
        <GridColumn header="USD Value" renderer={({ item }) => {
          const usdValue = item.balance * getTokenPrice(item.token);
          return `$${usdValue.toFixed(2)}`;
        }} />
      </Grid>
      <div>Total Portfolio Value: ${totalValue.toFixed(2)}</div>
      <Details summary="Portfolio Insights">
        <ul>
          {insights.map((insight, index) => (
            <li key={index}>{insight}</li>
          ))}
        </ul>
      </Details>
    </div>
  );
}