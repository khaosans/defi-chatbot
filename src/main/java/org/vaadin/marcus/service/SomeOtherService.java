package org.vaadin.marcus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.marcus.client.Web3Service;

@Service
public class SomeOtherService {
    private final Web3Service web3Service;

    @Autowired
    public SomeOtherService(Web3Service web3Service) {
        this.web3Service = web3Service;
    }

    public void performAction() {
        try {
            String walletAddress = web3Service.connectWallet();
            // Do something with the wallet address
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}