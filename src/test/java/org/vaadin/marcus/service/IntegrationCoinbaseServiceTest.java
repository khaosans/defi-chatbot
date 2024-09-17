package org.vaadin.marcus.service;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationCoinbaseServiceTest {

    private CoinbaseService coinbaseService;
    private String apiKey;
    private String apiSecret;
    private String apiPassphrase;

    @BeforeEach
    void setUp() {
        apiKey = System.getenv("COINBASE_API_KEY");
        apiSecret = System.getenv("COINBASE_API_SECRET");
        apiPassphrase = System.getenv("COINBASE_API_PASSPHRASE");

        if (apiKey == null || apiSecret == null || apiPassphrase == null) {
            System.out.println("Skipping tests: One or more Coinbase API credentials are not set in the environment variables.");
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
        if (coinbaseService == null) {
            System.out.println("Skipping test: CoinbaseService not initialized due to missing credentials.");
            return;
        }

        System.out.println("Running testGetPortfolioValue");
        assertNotNull(coinbaseService, "CoinbaseService should be initialized");
        Double portfolioValue = coinbaseService.getPortfolioValue();
        assertNotNull(portfolioValue, "Portfolio value should not be null");
        assertTrue(portfolioValue >= 0, "Portfolio value should be non-negative");
    }
}