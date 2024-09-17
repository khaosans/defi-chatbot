package org.vaadin.marcus.client;

import org.vaadin.marcus.service.CoinbaseService;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import java.util.Optional;
import org.vaadin.marcus.service.AccountDetails;

import java.util.List;

@BrowserCallable
@AnonymousAllowed
public class PorfolioService {

    private final CoinbaseService coinbaseService;

    public PorfolioService(CoinbaseService coinbaseService) {
        this.coinbaseService = coinbaseService;
    }

    public Optional<Double> getPortfolioValue() {
        return Optional.ofNullable(coinbaseService.getPortfolioValue());
    
    }

    public List<AccountDetails> getAccountDetails() {
        return coinbaseService.getAccountDetails();
    }
}
