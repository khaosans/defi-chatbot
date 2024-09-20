package org.vaadin.marcus.model;

import java.math.BigDecimal;

public class TokenBalance {
    private String token;
    private BigDecimal balance;

    // Constructor, getters, and setters
    public TokenBalance(String token, BigDecimal balance) {
        this.token = token;
        this.balance = balance;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    // ... other methods if needed ...
}