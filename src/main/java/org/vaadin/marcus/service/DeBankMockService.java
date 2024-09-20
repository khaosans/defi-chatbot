package org.vaadin.marcus.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vaadin.marcus.model.TokenBalance;

@Service
public class DeBankMockService {

    private Map<String, List<TokenBalance>> chainBalances;
    private Map<String, BigDecimal> tokenPrices;

    public DeBankMockService() {
        initializeMockData();
    }

    private void initializeMockData() {
        chainBalances = new HashMap<>();
        tokenPrices = new HashMap<>();

        // Ethereum balances
        chainBalances.put("ethereum", Arrays.asList(
            new TokenBalance("ETH", new BigDecimal("2.5")),
            new TokenBalance("USDC", new BigDecimal("1000.0")),
            new TokenBalance("LINK", new BigDecimal("50.0"))
        ));

        // Binance Smart Chain balances
        chainBalances.put("bsc", Arrays.asList(
            new TokenBalance("BNB", new BigDecimal("10.0")),
            new TokenBalance("CAKE", new BigDecimal("100.0")),
            new TokenBalance("BUSD", new BigDecimal("500.0"))
        ));

        // Polygon balances
        chainBalances.put("polygon", Arrays.asList(
            new TokenBalance("MATIC", new BigDecimal("1000.0")),
            new TokenBalance("AAVE", new BigDecimal("5.0")),
            new TokenBalance("USDT", new BigDecimal("750.0"))
        ));

        // Mock token prices in USD
        tokenPrices.put("ETH", BigDecimal.valueOf(2000));
        tokenPrices.put("USDC", BigDecimal.ONE);
        tokenPrices.put("LINK", BigDecimal.valueOf(15));
        tokenPrices.put("BNB", BigDecimal.valueOf(300));
        tokenPrices.put("CAKE", BigDecimal.valueOf(5));
        tokenPrices.put("BUSD", BigDecimal.ONE);
        tokenPrices.put("MATIC", BigDecimal.valueOf(0.80));
        tokenPrices.put("AAVE", BigDecimal.valueOf(100));
        tokenPrices.put("USDT", BigDecimal.ONE);
    }

    public List<TokenBalance> getBalancesByChain(String chain) {
        return chainBalances.getOrDefault(chain.toLowerCase(), new ArrayList<>());
    }

    public BigDecimal getTokenPrice(String token) {
        return tokenPrices.getOrDefault(token.toUpperCase(), BigDecimal.ZERO);
    }

    public BigDecimal getTotalPortfolioValue() {
        return chainBalances.values().stream()
            .flatMap(List::stream)
            .map(balance -> balance.getBalance().multiply(getTokenPrice(balance.getToken())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Removed generatePortfolioInsights, calculateChainValues, findBestPerformingAsset, and findStakingOpportunities
    // to simplify the service and focus on the core functionality
}