package org.sour.service;

public class AccountDetails {
    private String accountNumber;
    private String accountType;
    private String balance;

    public AccountDetails(String accountNumber, String accountType, String balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getBalance() { return balance; }
    public void setBalance(String balance) { this.balance = balance; }
}