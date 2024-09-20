package org.vaadin.marcus.langchain4j;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.vaadin.marcus.model.TokenBalance;
import org.vaadin.marcus.service.DeBankMockService;
import org.springframework.web.client.RestTemplate;

import dev.langchain4j.agent.tool.Tool;

@Component
public class LangChain4jTools {

    private final DeBankMockService deBankMockService;
    private final RestTemplate restTemplate;
    private final String debankApiKey;

    public LangChain4jTools(DeBankMockService deBankMockService, RestTemplate restTemplate, @Value("${debank.api.key}") String debankApiKey) {
        this.deBankMockService = deBankMockService;
        this.restTemplate = restTemplate;
        this.debankApiKey = debankApiKey;
    }

    @Tool("Retrieve DeFi account balances for a specific chain")
    public List<TokenBalance> getDeFiAccountBalances(String chain) {
        return deBankMockService.getBalancesByChain(chain);
    }

    @Tool("Get total portfolio value in USD")
    public BigDecimal getTotalPortfolioValueUSD() {
        return deBankMockService.getTotalPortfolioValue();
    }

    @Tool("Get USD price for a specific token")
    public BigDecimal getTokenUSDPrice(String token) {
        return deBankMockService.getTokenPrice(token);
    }

    @Tool("Generate portfolio insights")
    public List<String> generatePortfolioInsights() {
        return deBankMockService.generatePortfolioInsights();
    }

    public Map<String, List<TokenBalance>> fetchDeFiBalances(String userId) {
        // Mocked data for DeFi balances
        Map<String, List<TokenBalance>> balances = new HashMap<>();
        balances.put("Ethereum", Arrays.asList(
            new TokenBalance("ETH", 10.5),
            new TokenBalance("USDC", 1000.0)
        ));
        balances.put("Binance", Arrays.asList(
            new TokenBalance("BNB", 5.2)
        ));
        balances.put("Polygon", Arrays.asList(
            new TokenBalance("MATIC", 500.0)
        ));
        return balances;
    }

    public String getChainInfo(String chainId) {
        String url = "https://pro-openapi.debank.com/v1/chain?id=" + chainId;
        return restTemplate.getForObject(url, String.class);
    }
}
