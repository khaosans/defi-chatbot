package org.vaadin.marcus.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            new TokenBalance("ETH", 2.5),
            new TokenBalance("USDC", 1000.0),
            new TokenBalance("LINK", 50.0)
        ));

        // Binance Smart Chain balances
        chainBalances.put("bsc", Arrays.asList(
            new TokenBalance("BNB", 10.0),
            new TokenBalance("CAKE", 100.0),
            new TokenBalance("BUSD", 500.0)
        ));

        // Polygon balances
        chainBalances.put("polygon", Arrays.asList(
            new TokenBalance("MATIC", 1000.0),
            new TokenBalance("AAVE", 5.0),
            new TokenBalance("USDT", 750.0)
        ));

        // Mock token prices in USD
        tokenPrices.put("ETH", new BigDecimal("2000"));
        tokenPrices.put("USDC", BigDecimal.ONE);
        tokenPrices.put("LINK", new BigDecimal("15"));
        tokenPrices.put("BNB", new BigDecimal("300"));
        tokenPrices.put("CAKE", new BigDecimal("5"));
        tokenPrices.put("BUSD", BigDecimal.ONE);
        tokenPrices.put("MATIC", new BigDecimal("0.80"));
        tokenPrices.put("AAVE", new BigDecimal("100"));
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

    public List<String> generatePortfolioInsights() {
        List<String> insights = new ArrayList<>();
        
        // Calculate total portfolio value
        BigDecimal totalValue = getTotalPortfolioValue();
        insights.add("Your total portfolio value is $" + totalValue.setScale(2, RoundingMode.HALF_UP));

        // Calculate diversification
        Map<String, BigDecimal> chainValues = calculateChainValues();
        String mostValuableChain = chainValues.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
        BigDecimal mostValuableChainPercentage = chainValues.get(mostValuableChain).divide(totalValue, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
        insights.add(String.format("Your portfolio is most heavily weighted in %s at %.2f%% of total value.", mostValuableChain, mostValuableChainPercentage));

        // Suggest diversification if needed
        if (mostValuableChainPercentage.compareTo(new BigDecimal("70")) > 0) {
            insights.add("Consider diversifying your portfolio across more chains to reduce risk.");
        }

        // Identify best performing asset
        String bestPerformingAsset = findBestPerformingAsset();
        insights.add("Your best performing asset is " + bestPerformingAsset + ".");

        // Suggest staking opportunities
        List<String> stakingOpportunities = findStakingOpportunities();
        if (!stakingOpportunities.isEmpty()) {
            insights.add("Consider staking opportunities for: " + String.join(", ", stakingOpportunities));
        }

        return insights;
    }

    private Map<String, BigDecimal> calculateChainValues() {
        return chainBalances.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(balance -> balance.getBalance().multiply(getTokenPrice(balance.getToken())))
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ));
    }

    private String findBestPerformingAsset() {
        return chainBalances.values().stream()
                .flatMap(List::stream)
                .max(Comparator.comparing(balance -> balance.getBalance().multiply(getTokenPrice(balance.getToken()))))
                .map(TokenBalance::getToken)
                .orElse("");
    }

    private List<String> findStakingOpportunities() {
        // Mock implementation - in a real scenario, this would check current staking rates and opportunities
        return Arrays.asList("ETH", "MATIC", "CAKE");
    }
}