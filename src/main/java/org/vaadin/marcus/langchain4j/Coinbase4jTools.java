package org.vaadin.marcus.langchain4j;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import org.vaadin.marcus.service.CoinbaseService;
import org.vaadin.marcus.service.AccountDetails;

import java.util.List;

@Component
public class Coinbase4jTools {
    
    private final CoinbaseService coinbaseService;

    public Coinbase4jTools(CoinbaseService coinbaseService) {
        this.coinbaseService = coinbaseService;
    }
   
    @Tool("Retrieves the total value of the portfolio in USD.")
    public Double getPortfolioValue() {
        return coinbaseService.getPortfolioValue();
    }

    @Tool("Retrieves the account details including individual account balances and available amounts.")
    public List<AccountDetails> getAccountDetails() {
        return coinbaseService.getAccountDetails();
    }
   
    @Tool("Retrieves the current price of a given cryptocurrency in USD.")
    public Double getCurrentPrice(String currency) {
        return coinbaseService.getCurrentPrice(currency);
    }
}