package org.vaadin.marcus.model;

public class DeFiBalance {
    private String chain;
    private String token;
    private double balance;

    // Constructor, getters, and setters
    public DeFiBalance(String chain, String token, double balance) {
        this.chain = chain;
        this.token = token;
        this.balance = balance;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

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