package org.sour.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class Web3Service {

    private List<AccountBalance> accountBalances;
    private Map<String, BigDecimal> usdValues;

    public Web3Service() {
        initializeMockData();
    }

    private void initializeMockData() {
        accountBalances = new ArrayList<>();
        accountBalances.add(new AccountBalance("Ethereum", "ETH", new BigDecimal("10.5")));
        accountBalances.add(new AccountBalance("Ethereum", "USDC", new BigDecimal("1000")));
        accountBalances.add(new AccountBalance("Binance Smart Chain", "BNB", new BigDecimal("5.2")));
        accountBalances.add(new AccountBalance("Binance Smart Chain", "CAKE", new BigDecimal("100")));
        accountBalances.add(new AccountBalance("Polygon", "MATIC", new BigDecimal("500")));
        accountBalances.add(new AccountBalance("Polygon", "AAVE", new BigDecimal("10")));

        usdValues = new HashMap<>();
        usdValues.put("ETH", new BigDecimal("2000.00"));
        usdValues.put("USDC", new BigDecimal("1.00"));
        usdValues.put("BNB", new BigDecimal("300.00"));
        usdValues.put("CAKE", new BigDecimal("5.00"));
        usdValues.put("MATIC", new BigDecimal("0.80"));
        usdValues.put("AAVE", new BigDecimal("100.00"));
    }

    public List<AccountBalance> getAccountBalances() {
        return accountBalances;
    }

    public List<AccountBalance> getAccountBalancesByChain(String chain) {
        return accountBalances.stream()
                .filter(balance -> balance.getChain().equalsIgnoreCase(chain))
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalPortfolioValueUSD() {
        return accountBalances.stream()
                .map(balance -> balance.getBalance().multiply(usdValues.getOrDefault(balance.getToken(), BigDecimal.ZERO)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, BigDecimal> getUSDValues() {
        return usdValues;
    }

    public static class AccountBalance {
        private String chain;
        private String token;
        private BigDecimal balance;

        public AccountBalance(String chain, String token, BigDecimal balance) {
            this.chain = chain;
            this.token = token;
            this.balance = balance;
        }

        // Getters and setters
        public String getChain() { return chain; }
        public void setChain(String chain) { this.chain = chain; }
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
        public BigDecimal getBalance() { return balance; }
        public void setBalance(BigDecimal balance) { this.balance = balance; }
    }
}