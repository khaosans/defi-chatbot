package org.vaadin.marcus.service;

import org.springframework.stereotype.Service;
import org.vaadin.marcus.service.CoinbaseService;
import org.vaadin.marcus.service.AccountDetails;

import java.util.List;

@Service
public class PortfolioService {

    private final CoinbaseService coinbaseService;

    public PortfolioService(CoinbaseService coinbaseService) {
        this.coinbaseService = coinbaseService;
    }

    public Double getTotalPortfolioValue() {
        return coinbaseService.getPortfolioValue();
    }

    public List<AccountDetails> getAllAccountDetails() {
        return coinbaseService.getAccountDetails();
    }

    public Double getCryptoCurrencyPrice(String currency) {
        return coinbaseService.getCurrentPrice(currency);
    }

    // You can add more portfolio-related methods here as needed
    // For example:
    // public void updatePortfolio(...)
    // public void addToPortfolio(...)
    // public void removeFromPortfolio(...)
}