package org.sour.tool;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.sour.service.AccountDetails;
import org.sour.service.CoinbaseService;

import dev.langchain4j.agent.tool.Tool;

@Component
public class Coinbase4jTools {
    
    private final CoinbaseService coinbaseService;
    private AccountDetails cachedAccount;
    private long lastFetchTime;
    private static final long CACHE_DURATION = TimeUnit.MINUTES.toMillis(5); // Cache for 5 minutes

    public Coinbase4jTools(CoinbaseService coinbaseService) {
        if (coinbaseService == null) {
            throw new IllegalArgumentException("CoinbaseService cannot be null");
        }
        this.coinbaseService = coinbaseService;
    }

    // Mock authentication method
    private boolean mockAuthenticate() {
        // Simulate successful authentication
        return true; // Change this logic as needed for your mock
    }

    @Tool("Get account")
    public AccountDetails getAccount() {
        if (!mockAuthenticate()) {
            System.err.println("Authentication failed");
            return null; // Or handle as needed
        }
        
        long currentTime = System.currentTimeMillis();
        if (cachedAccount == null || (currentTime - lastFetchTime) > CACHE_DURATION) {
            try {
                System.out.println("Fetching fresh account from Coinbase API");
                cachedAccount = coinbaseService.getAccounts();
                lastFetchTime = currentTime;
            } catch (IOException ex) {
                System.err.println("Error fetching account: " + ex.getMessage());
                return cachedAccount;
            }
        } else {
            System.out.println("Returning cached account");
        }
        return cachedAccount;
    }
}
