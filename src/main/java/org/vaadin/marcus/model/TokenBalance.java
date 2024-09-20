package org.vaadin.marcus.model;

public class TokenBalance {
    private String token;
    private double balance;

    // Constructor, getters, and setters
    public TokenBalance(String token, double balance) {
        this.token = token;
        this.balance = balance;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}