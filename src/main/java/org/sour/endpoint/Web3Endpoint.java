package org.sour.endpoint;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthChainId;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

@BrowserCallable
@AnonymousAllowed
@RestController
public class Web3Endpoint {

    private final Web3j web3j;

    @Autowired
    public Web3Endpoint(Web3j web3j) {
        this.web3j = web3j;
    }

    public String getWalletAddress() {
        try {
            EthAccounts accounts = web3j.ethAccounts().send();
            List<String> addresses = accounts.getAccounts();
            if (!addresses.isEmpty()) {
                return addresses.get(0); // Return the first account (index [0])
            } else {
                return ""; // No accounts found
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ""; // Handle exceptions and return empty string
        }
    }

    public String getCurrentNetwork() {
        try {
            EthChainId chainIdResponse = web3j.ethChainId().send();
            return chainIdResponse.getChainId().toString(); // Return the current network ID
        } catch (IOException e) {
            // e.printStackTrace();
            return ""; // Handle exceptions and return empty string
        }
    }

    // DTO for transaction request
    public static class TransactionRequest {
        private String from;
        private String to;
        private BigDecimal value;

        // Getters and Setters
        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }
    }
}