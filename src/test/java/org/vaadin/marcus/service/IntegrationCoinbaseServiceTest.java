package org.vaadin.marcus.service;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationCoinbaseServiceTest {

    private CoinbaseService coinbaseService;

    @BeforeEach
    void setUp() {
        String apiKey = System.getenv("COINBASE_API_KEY");
        String apiSecret = System.getenv("COINBASE_API_SECRET");
        String apiPassphrase = System.getenv("COINBASE_API_PASSPHRASE");

        if (apiKey == null || apiSecret == null || apiPassphrase == null) {
            System.out.println("Skipping setup: One or more Coinbase API credentials are not set in the environment variables.");
            return;
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();

        coinbaseService = new CoinbaseService(
            okHttpClient,
            gson,
            "https://api.exchange.coinbase.com",
            apiKey,
            apiSecret,
            apiPassphrase
        );
    }

    @Test
    void testGetPortfolioValue() {
        String apiKey = System.getenv("{COINBASE_API_KEY}");
        String apiSecret = System.getenv("{COINBASE_API_SECRET}");
        String apiPassphrase = System.getenv("{COINBASE_API_PASSPHRASE}");

        if (apiKey == null || apiSecret == null || apiPassphrase == null) {
            System.out.println("Skipping setup: One or more Coinbase API credentials are not set in the environment variables.");
            return;
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();

        coinbaseService = new CoinbaseService(
            okHttpClient,
            gson,
            "https://api.exchange.coinbase.com",
            apiKey,
            apiSecret,
            apiPassphrase
        );
    
        assertNotNull(coinbaseService, "CoinbaseService should be initialized");
        Double portfolioValue = coinbaseService.getPortfolioValue();
        assertNotNull(portfolioValue, "Portfolio value should not be null");
        assertTrue(portfolioValue >= 0, "Portfolio value should be non-negative");
    }

  
}