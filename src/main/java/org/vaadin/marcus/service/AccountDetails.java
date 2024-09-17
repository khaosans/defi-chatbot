package org.vaadin.marcus.service;

public class AccountDetails {
    // Add account-related fields and methods here
    private String accountNumber;
    private String accountType;
    private String balance;

    public AccountDetails(String accountNumber, String accountType, String balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }
}