package org.vaadin.marcus.client;

import java.util.List;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.http.HttpService;

public class Web3Service {
    private final Web3j web3j;
    private String userAddress;

    public Web3Service() {
        // Initialize Web3j with Infura API URL
        this.web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/b008648b7d4a4944a196e4d5c59686e3"));
    }

    public String connectWallet() throws Exception {
        EthAccounts accounts = web3j.ethAccounts().send();
        List<String> addresses = accounts.getAccounts();
        if (!addresses.isEmpty()) {
            userAddress = addresses.get(0);
            System.out.println("Connected to wallet: " + userAddress);
            return userAddress;
        } else {
            throw new Exception("No wallet connected");
        }
    }

    public String getWalletAddress() {
        return userAddress; // Return the stored wallet address
    }

    // Other methods...
}