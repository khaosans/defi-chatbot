package org.vaadin.marcus.langchain4j;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.vaadin.marcus.service.AccountDetails;
import org.vaadin.marcus.service.CoinbaseService;

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

    @Tool("Get account")
    public AccountDetails getAccount() {
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